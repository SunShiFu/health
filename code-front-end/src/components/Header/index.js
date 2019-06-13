import React from 'react'
import { Row,Col } from "antd"
import './index.less'
import { Menu, Icon } from 'antd';
import { NavLink } from 'react-router-dom'
import { switchMenu, saveBtnList } from './../../redux/action'

import { connect } from 'react-redux'
import MenuConfig from './../../config/menuConfig'

class Header extends React.Component{
    state={}
    componentWillMount(){
        const menuTreeNode = this.renderMenu(MenuConfig);
        this.setState({
            menuTreeNode
        });
    }

    renderMenu =(data)=>{
        return data.map((item)=>{
            if(item.children)
                {
                    return (
                        <SubMenu title={item.title} key={item.key} className="menu">
                        {
                            this.renderMenu(item.children)
                        }
                        </SubMenu>
                        )
                }

        return <Menu.Item title={item.title} key={item.key}>
        <NavLink to={item.key}>{item.title}
        </NavLink>
        </Menu.Item>
        })
    }

handleClick = ({ item, key }) => {
    if (key == this.state.currentKey) {
        return false;
    }
    const { dispatch } = this.props;
    dispatch(switchMenu(item.props.title));

    this.setState({
        currentKey: key
    });
};

    render(){
        const { menuName, menuType } = this.props;
        return (
            <div className="header">

                <Row className="header-bottom">
                    <Menu mode="horizontal"
                    onClick={this.handleClick}
                    theme="light"
                    >
                    { this.state.menuTreeNode }
                    </Menu>

                </Row>

                {
                    menuType?'':
                        <Row className="breadcrumb">
                            <Col span="4" className="breadcrumb-title">
                                {menuName || '首页'}
                            </Col>
                        </Row>
                }
            </div>
        );
    }
}
const mapStateToProps = state => {
    return {
        menuName: state.menuName
    }
};
export default connect(mapStateToProps)(Header)