

import React, { Component } from 'react'
import FontAwesome from 'react-fontawesome';
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from 'components/BooleanOption';
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
import styles from './Message.dashboard.less'
import DescriptionList from '../../components/DescriptionList';
import ImagePreview from '../../components/ImagePreview';
import GlobalComponents from '../../custcomponents';
import DashboardTool from '../../common/Dashboard.tool'
import appLocaleName from '../../common/Locale.tool'

const {aggregateDataset,calcKey, defaultHideCloseTrans,
  defaultImageListOf,defaultSettingListOf,defaultBuildTransferModal,
  defaultExecuteTrans,defaultHandleTransferSearch,defaultShowTransferModel,
  defaultRenderExtraHeader,
  defaultSubListsOf,
  defaultRenderExtraFooter,renderForTimeLine,renderForNumbers
}= DashboardTool



const { Description } = DescriptionList;
const { TabPane } = Tabs
const { RangePicker } = DatePicker
const { Option } = Select


const imageList =(message)=>{return [
	 ]}

const internalImageListOf = (message) =>defaultImageListOf(message,imageList)

const optionList =(message)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalSettingListOf = (message) =>defaultSettingListOf(message, optionList)
const internalLargeTextOf = (message) =>{

	return(<div> 
   <Card title={`Content`} ><pre>{message.content}</pre></Card>
</div>)

	

}


const internalRenderExtraHeader = defaultRenderExtraHeader

const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf


const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <FontAwesome name="arrow-left"  /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}


const internalSummaryOf = (message,targetComponent) =>{
	
	
	const {MessageService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="Id">{message.id}</Description> 
<Description term="Title">{message.title}</Description> 
<Description term="Send Time">{ moment(message.sendTime).format('YYYY-MM-DD')}</Description> 
<Description term="Read Time">{ moment(message.readTime).format('YYYY-MM-DD')}</Description> 
<Description term="Sender">{message.sender==null?appLocaleName(userContext,"NotAssigned"):message.sender.displayName}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"Sender","profile",MessageService.requestCandidateSender,
	      MessageService.transferToAnotherSender,"anotherSenderId",message.sender?message.sender.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="Receiver">{message.receiver==null?appLocaleName(userContext,"NotAssigned"):message.receiver.displayName}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"Receiver","profile",MessageService.requestCandidateReceiver,
	      MessageService.transferToAnotherReceiver,"anotherReceiverId",message.receiver?message.receiver.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="Status">{message.status}</Description> 
	
        {buildTransferModal(message,targetComponent)}
      </DescriptionList>
	)

}


class MessageDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'message'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName,  } = this.props.message
    if(!this.props.message.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"Message",cardsFor: "message",
    	cardsSource: this.props.message,returnURL,displayName,
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
    return (

      <PageHeaderLayout
        title={renderTitle(cardsData,this)}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
      {renderExtraHeader(cardsData.cardsSource)}
        <div>
        {settingListOf(cardsData.cardsSource)}
        {imageListOf(cardsData.cardsSource)}
        {subListsOf(cardsData)} 
        {largeTextOf(cardsData.cardsSource)}
          
        </div>
      </PageHeaderLayout>
    )
  }
}

export default connect(state => ({
  message: state._message,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(MessageDashboard))

