import React from 'react'
import { Row,Col } from 'antd';
import Header from './components/Header'
import Footer from './components/Footer'
import { connect } from 'react-redux'
import './style/common.less'
class Admin extends React.Component{

    render(){
        return (
            <Row className="container">
                <Col className="main">
                    <Header/>
                    <Row className="content">
                        {this.props.children}
                    </Row>
                    <Footer/>
                </Col>
            </Row>
        );
    }
}
export default connect()(Admin)