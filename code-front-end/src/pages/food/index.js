import React from 'react'
import { Card, Button, Table, Form, Input, Checkbox,Select,Radio, Icon, message, Modal, DatePicker } from 'antd'
import axios from '../../axios/index'
import Utils from '../../utils/utils'
import ETable from '../../components/ETable/index'
import Moment from 'moment'
const FormItem = Form.Item;
const Option = Select.Option;
const RadioGroup = Radio.Group;
export default class Food extends React.Component{

    state = {
        list:[]
    };

    params = {
        pageNum:1,
        pageSize:5
    };

    requestList = ()=>{
        axios.ajax({
            url:'/food/list',
            method:'get',
            data:{
                params:{
                    pageNum:this.params.pageNum,
                    pageSize:this.params.pageSize
                }
            }
        }).then((res)=>{

            let _this = this;
            this.setState({
                list:res.result.list.map((item,index)=>{
                    item.key=index;
                    return item;
                }),
                pagination:Utils.pagination(res,(current)=>{
                    _this.params.pageNum = current;
                    _this.requestList();
                })
            })
        })
    }

    componentDidMount(){
        this.requestList();
    }

    // 操作食品
    handleOperator = (type)=>{
        let item = this.state.selectedItem;
        if(type =='create'){
            this.setState({
                title:'创建食品',
                isVisible:true,
                type
            })
        }else if(type=="edit" || type=='detail'){
            if(!item){
                Modal.info({
                    title: '信息',
                    content: '请选择一个食品'
                });
                return;
            }
            this.setState({
                title:type=='edit'?'编辑食品':'查看详情',
                isVisible:true,
                userInfo:item,
                type
            })
        }else if(type=="delete"){
            if(!item){
                Modal.info({
                    title: '信息',
                    content: '请选择一个食品'
                });
                return;
            }
            Modal.confirm({
                title:'确定要删除此食品吗？',
                onOk:()=>{
                    axios.ajax({
                        url:'/food/delete',
                        method:'delete',
                        data:{
                            params:{
                                id:item.id
                            }
                        }
                    }).then((res)=>{
                        if(res.code ==0){
                            this.setState({
                                isVisible:false
                            });
                            this.requestList();
                        }
                    })
                },
                onCancel:()=>{

                }
            })
        }
    };

    handleSubmit = ()=>{
        let type = this.state.type;
        let data = this.userForm.props.form.getFieldsValue();
        if (type == 'edit') {
            data.id = this.state.selectedItem.id;
        }
        axios.ajax({
            url:type == 'create'?'/food/add':'/food/edit',
            method:type == 'create'?'post':'put',
            data:{
                params:{
                    ...data
                }
            }
        }).then((res)=>{
            if(res.code ==0){
                this.setState({
                    isVisible:false
                });
                this.requestList();
            }
        })
    };

    render(){

        const columns = [{
            title: 'id',
            dataIndex: 'id'
          }, {
            title: '名称',
            dataIndex: 'name'
          }, {
            title: '热量(千卡/500g)',
            dataIndex: 'foodEnergy'
        },{
            title: '描述',
            dataIndex: 'description'
        }];
        return (
            <div>
                <Card style={{marginTop:10}}>
                    <Button type="primary" icon="plus" onClick={()=>this.handleOperator('create')}>创建食品</Button>
                    <Button icon="edit" onClick={()=>this.handleOperator('edit')}>编辑食品</Button>
                    <Button onClick={()=>this.handleOperator('detail')}>食品详情</Button>
                    <Button type="danger" icon="delete" onClick={()=>this.handleOperator('delete')}>删除食品</Button>
                </Card>
                <div className="content-wrap">
                    <ETable
                        columns={columns}
                        updateSelectedItem={Utils.updateSelectedItem.bind(this)}
                        selectedRowKeys={this.state.selectedRowKeys}
                        dataSource={this.state.list}
                        pagination={this.state.pagination}
                    />
                </div>
                <Modal
                    title={this.state.title}
                    visible={this.state.isVisible}
                    onOk={this.handleSubmit}
                    width={800}
                    onCancel={()=>{
                        this.userForm.props.form.resetFields();
                        this.setState({
                            isVisible:false,
                            userInfo:''
                        })
                    }}
                >
                    <UserForm userInfo={this.state.userInfo} type={this.state.type} wrappedComponentRef={(inst) => this.userForm = inst }/>
                </Modal>
            </div>
        );
    }
}
class UserForm extends React.Component{

       render(){
        const { getFieldDecorator } = this.props.form;
        const formItemLayout = {
            labelCol: {span: 5},
            wrapperCol: {span: 16}
        };
        const userInfo = this.props.userInfo || {};
        const type = this.props.type;
        return (
            <Form layout="horizontal">

                <FormItem label="名称" {...formItemLayout}>
                    {
                        userInfo && type=='detail'?userInfo.name:
                        getFieldDecorator('name',{
                            initialValue:userInfo.name
                        })(
                            <Input type="text" placeholder="请输入名称"/>
                        )
                    }
                </FormItem>

                <FormItem label="热量(千卡/500g)" {...formItemLayout}>
                    {
                        userInfo && type=='detail'?userInfo.foodEnergy:
                            getFieldDecorator('foodEnergy',{
                                initialValue:userInfo.foodEnergy
                            })(
                                <Input type="text" placeholder="请输入热量(千卡/500g)"/>
                            )
                    }
                </FormItem>
                <FormItem label="描述" {...formItemLayout}>
                    {
                        userInfo && type=='detail'?userInfo.description:
                            getFieldDecorator('description',{
                                initialValue:userInfo.description
                            })(
                                <Input.TextArea rows={3} placeholder="请输入描述"/>
                            )
                    }
                </FormItem>
            </Form>
        );
    }
}
UserForm = Form.create({})(UserForm);