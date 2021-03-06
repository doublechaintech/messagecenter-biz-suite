

import React, { Component } from 'react'
import FontAwesome from 'react-fontawesome';
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'
import {
  ChartCard, yuan, MiniArea, MiniBar, MiniProgress, Field, Bar, Pie, TimelineChart,
} from '../../components/Charts'
import Trend from '../../components/Trend'
import NumberInfo from '../../components/NumberInfo'
import { getTimeDistance } from '../../utils/utils'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './PrivateMessage.dashboard.less'
import DescriptionList from '../../components/DescriptionList';
import ImagePreview from '../../components/ImagePreview';
import GlobalComponents from '../../custcomponents';
import DashboardTool from '../../common/Dashboard.tool'
import appLocaleName from '../../common/Locale.tool'

const {aggregateDataset,calcKey, defaultHideCloseTrans,
  defaultImageListOf,defaultSettingListOf,defaultBuildTransferModal,
  defaultExecuteTrans,defaultHandleTransferSearch,defaultShowTransferModel,
  defaultRenderExtraHeader,
  defaultSubListsOf,defaultRenderAnalytics,
  defaultRenderExtraFooter,renderForTimeLine,renderForNumbers,
  defaultQuickFunctions, defaultRenderSubjectList,
}= DashboardTool



const { Description } = DescriptionList;
const { TabPane } = Tabs
const { RangePicker } = DatePicker
const { Option } = Select


const imageList =(privateMessage)=>{return [
	 ]}

const internalImageListOf = (privateMessage) =>defaultImageListOf(privateMessage,imageList)

const optionList =(privateMessage)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (privateMessage) =>defaultSettingListOf(privateMessage, optionList)
const internalLargeTextOf = (privateMessage) =>{

	return(<div> 
   <Card title={`内容`} ><pre>{privateMessage.content}</pre></Card>
</div>)

	

}


const internalRenderExtraHeader = defaultRenderExtraHeader

const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf


const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <FontAwesome name="arrow-left"  /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}


const internalSummaryOf = (privateMessage,targetComponent) =>{
	
	
	const {PrivateMessageService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{privateMessage.id}</Description> 
<Description term="标题">{privateMessage.title}</Description> 
<Description term="发送时间">{ moment(privateMessage.sendTime).format('YYYY-MM-DD')}</Description> 
<Description term="阅读时间">{ moment(privateMessage.readTime).format('YYYY-MM-DD')}</Description> 
<Description term="发送方">{privateMessage.sender==null?appLocaleName(userContext,"NotAssigned"):`${privateMessage.sender.displayName}(${privateMessage.sender.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"发送方","profile",PrivateMessageService.requestCandidateSender,
	      PrivateMessageService.transferToAnotherSender,"anotherSenderId",privateMessage.sender?privateMessage.sender.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="收货人">{privateMessage.receiver==null?appLocaleName(userContext,"NotAssigned"):`${privateMessage.receiver.displayName}(${privateMessage.receiver.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"收货人","profile",PrivateMessageService.requestCandidateReceiver,
	      PrivateMessageService.transferToAnotherReceiver,"anotherReceiverId",privateMessage.receiver?privateMessage.receiver.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="状态">{privateMessage.status}</Description> 
	
        {buildTransferModal(privateMessage,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class PrivateMessageDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'privateMessage'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName,  } = this.props.privateMessage
    if(!this.props.privateMessage.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"私信",cardsFor: "privateMessage",
    	cardsSource: this.props.privateMessage,returnURL,displayName,
  		subItems: [
    
      	],
  	};
    
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const settingListOf = this.props.settingListOf || internalSettingListOf
    const imageListOf = this.props.imageListOf || internalImageListOf
    const subListsOf = this.props.subListsOf || internalSubListsOf
    const largeTextOf = this.props.largeTextOf ||internalLargeTextOf
    const summaryOf = this.props.summaryOf || internalSummaryOf
    const renderTitle = this.props.renderTitle || internalRenderTitle
    const renderExtraFooter = this.props.renderExtraFooter || internalRenderExtraFooter
    const renderAnalytics = this.props.renderAnalytics || defaultRenderAnalytics
    const quickFunctions = this.props.quickFunctions || internalQuickFunctions
    const renderSubjectList = this.props.renderSubjectList || internalRenderSubjectList
    
    return (

      <PageHeaderLayout
        title={renderTitle(cardsData,this)}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
       
        {renderExtraHeader(cardsData.cardsSource)}
        {quickFunctions(cardsData)} 
        {renderAnalytics(cardsData.cardsSource)}
        {settingListOf(cardsData.cardsSource)}
        {imageListOf(cardsData.cardsSource)}  
        {renderSubjectList(cardsData)}       
        {largeTextOf(cardsData.cardsSource)}
        {renderExtraFooter(cardsData.cardsSource)}
  		
      </PageHeaderLayout>
    
    )
  }
}

export default connect(state => ({
  privateMessage: state._privateMessage,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(PrivateMessageDashboard))

