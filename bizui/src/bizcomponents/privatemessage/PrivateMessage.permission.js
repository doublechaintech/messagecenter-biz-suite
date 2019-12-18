

import React, { Component } from 'react'
import FontAwesome from 'react-fontawesome';
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './PrivateMessage.profile.less'
import DescriptionList from '../../components/DescriptionList';

import GlobalComponents from '../../custcomponents';
import PermissionSetting from '../../permission/PermissionSetting'
import appLocaleName from '../../common/Locale.tool'
const { Description } = DescriptionList;
const {defaultRenderExtraHeader}= DashboardTool


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////


const internalSummaryOf = (privateMessage,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{privateMessage.id}</Description> 
<Description term="标题">{privateMessage.title}</Description> 
<Description term="发送时间">{ moment(privateMessage.sendTime).format('YYYY-MM-DD')}</Description> 
<Description term="阅读时间">{ moment(privateMessage.readTime).format('YYYY-MM-DD')}</Description> 
<Description term="状态">{privateMessage.status}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = privateMessage => {
  const {PrivateMessageBase} = GlobalComponents
  return <PermissionSetting targetObject={privateMessage}  targetObjectMeta={PrivateMessageBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class PrivateMessagePermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  privateMessage = this.props.privateMessage;
    const { id,displayName,  } = privateMessage
    const cardsData = {cardsName:"私信",cardsFor: "privateMessage",cardsSource: privateMessage,
  		subItems: [
    
      	],
  	};
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const summaryOf = this.props.summaryOf || internalSummaryOf
   
    return (

      <PageHeaderLayout
        title={`${cardsData.cardsName}: ${displayName}`}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
      {renderExtraHeader(cardsData.cardsSource)}
      {renderPermissionSetting(cardsData.cardsSource)}
      
      </PageHeaderLayout>
    )
  }
}

export default connect(state => ({
  privateMessage: state._privateMessage,
}))(Form.create()(PrivateMessagePermission))

