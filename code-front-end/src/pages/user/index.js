import React from 'react'
import { Card, Button, Table, Form, Input, Checkbox,Select,Radio, Icon, message, Modal, DatePicker } from 'antd'
import axios from '../../axios/index'
import Utils from '../../utils/utils'
import ETable from '../../components/ETable/index'
import Moment from 'moment'
const FormItem = Form.Item;
const Option = Select.Option;
const RadioGroup = Radio.Group;
export default class User extends React.Component{

    state = {
        list:[]
    };

    params = {
        pageNum:1,
        pageSize:5
    };

    requestList = ()=>{
        axios.ajax({
            url:'/user/list',
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

    // 操作用户
    handleOperator = (type)=>{
        let item = this.state.selectedItem;
        if(type =='create'){
            this.setState({
                title:'创建用户',
                isVisible:true,
                type
            })
        }else if(type=="edit"){
            if(!item){
                Modal.info({
                    title: '信息',
                    content: '请选择一个用户'
                });
                return;
            }
            this.setState({
                title:type=='edit'?'编辑用户':'查看详情',
                isVisible:true,
                userInfo:item,
                type
            })
        }else if(type=="delete"){
            if(!item){
                Modal.info({
                    title: '信息',
                    content: '请选择一个用户'
                });
                return;
            }
            Modal.confirm({
                title:'确定要删除此用户吗？',
                onOk:()=>{
                    axios.ajax({
                        url:'/user/delete',
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
        } else if (type=='detail') {
            if(!item){
                Modal.info({
                    title: '信息',
                    content: '请选择一个用户'
                });
                return;
            }
            window.open(`/#/common/user/detail/${item.id}`,'_blank')

        }

    };

    handleSubmit = ()=>{
        let type = this.state.type;
        let data = this.userForm.props.form.getFieldsValue();
        if (type == 'edit') {
            data.id = this.state.selectedItem.id;
        }
        console.log(data)
        axios.ajax({

            url:type == 'create'?'/user/add':'/user/edit',
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
            title: '用户名',
            dataIndex: 'name'
          }, {
            title: '性别',
            dataIndex: 'gender',
            render(gender){
                return gender ==1 ?'男':'女'
            }
          }, {
            title: '年龄',
            dataIndex: 'age'
          }, {
            title: '工作',
            dataIndex: 'job',
            render(job){
                let config = {
                    '1':'后端开发',
                    '2':'前端开发',
                    '3':'移动端开发',
                    '4':'测试开发',
                    '5':'产品经理'
                };
                return config[job];
            }
          },{
            title: '家庭住址',
            dataIndex: 'address'
          },{
            title: '手机号码',
            dataIndex: 'phone'
          },{
            title: '创建时间',
            dataIndex: 'createTime'
          }];
        return (
            <div>
                <Card style={{marginTop:10}}>
                    <Button type="primary" icon="plus" onClick={()=>this.handleOperator('create')}>创建用户</Button>
                    <Button icon="edit" onClick={()=>this.handleOperator('edit')}>编辑用户</Button>
                    <Button onClick={()=>this.handleOperator('detail')}>用户详情</Button>
                    <Button type="danger" icon="delete" onClick={()=>this.handleOperator('delete')}>删除用户</Button>
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

    getState = (state)=>{
        return {
            '1':'后端开发',
            '2':'前端开发',
            '3':'移动端开发',
            '4':'测试开发',
            '5':'产品经理'
        }[state]
    };

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
                <FormItem label="姓名" {...formItemLayout}>
                    {
                        userInfo && type=='detail'?userInfo.name:
                        getFieldDecorator('name',{
                            initialValue:userInfo.name
                        })(
                            <Input type="text" placeholder="请输入姓名"/>
                        )
                    }
                </FormItem>
                <FormItem label="性别" {...formItemLayout}>
                    {
                        userInfo && type=='detail'?userInfo.gender==1?'男':'女':
                        getFieldDecorator('gender',{
                            initialValue:userInfo.gender
                        })(
                        <RadioGroup>
                            <Radio value={1}>男</Radio>
                            <Radio value={0}>女</Radio>
                        </RadioGroup>
                    )}
                </FormItem>
                <FormItem label="年龄" {...formItemLayout}>
                    {
                        userInfo && type=='detail'?userInfo.age:
                            getFieldDecorator('age',{
                                initialValue:userInfo.age
                            })(
                                <Input type="text" placeholder="请输入年龄"/>
                            )}
                </FormItem>
                <FormItem label="工作" {...formItemLayout}>
                    {
                        userInfo && type=='detail'?this.getState(userInfo.job):
                        getFieldDecorator('job',{
                            initialValue:userInfo.job
                        })(
                        <Select>
                            <Option value={1}>后端开发</Option>
                            <Option value={2}>前端开发</Option>
                            <Option value={3}>移动端开发</Option>
                            <Option value={4}>测试开发</Option>
                            <Option value={5}>产品经理</Option>
                        </Select>
                    )}
                </FormItem>
                <FormItem label="家庭住址" {...formItemLayout}>
                    {
                        userInfo && type=='detail'?userInfo.address:
                        getFieldDecorator('address',{
                            initialValue:userInfo.address
                        })(
                        <Input.TextArea rows={3} placeholder="请输入家庭住址"/>
                    )}
                </FormItem>
                <FormItem label="手机号码" {...formItemLayout}>
                    {
                        userInfo && type=='detail'?userInfo.phone:
                            getFieldDecorator('phone',{
                                initialValue:userInfo.phone
                            })(
                                <Input type="text" placeholder="请输入手机号码"/>
                            )}
                </FormItem>
            </Form>
        );
    }
}
UserForm = Form.create({})(UserForm);