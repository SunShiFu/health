import React from 'react'
import { HashRouter, Route, Switch, Redirect} from 'react-router-dom'
import App from './App'
import Admin from './admin'

import Home from './pages/home'
import Food from './pages/food/index'
import Sport from './pages/sport/index'
import User from './pages/user/index'
import UserDetail from './pages/echarts/index'

import Common from './common'

export default class ERouter extends React.Component{

    render(){
        return (
            <HashRouter>
                <App>
                    <Switch>
                        <Route path="/common" render={() =>
                            <Common>
                            <Route path="/common/user/detail/:userId" component={UserDetail} />
                            </Common>
                        }
                        />
                        <Route path="/" render={()=>
                            <Admin>
                                <Switch>
                                    <Route path='/home' component={Home} />
                                    <Route path="/food" component={Food} />
                                    <Route path="/sport" component={Sport} />
                                    <Route path='/user' component={User} />

                                    <Redirect to="/home" />
                                </Switch>
                            </Admin>         
                        } />
                    </Switch>
                </App>
            </HashRouter>
        );
    }
}