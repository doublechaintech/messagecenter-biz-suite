import React, { Component } from 'react'
import { Card, Button, Form, Icon, Col, Row, DatePicker, TimePicker, Input, Select, Popover,Switch,Modal } from 'antd'
import { connect } from 'dva'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import {ImageComponent} from '../../axios/tools'
import FooterToolbar from '../../components/FooterToolbar'
import styles from './Message.createform.less'
import {mapBackToImageValues, mapFromImageValues} from '../../axios/tools'
import GlobalComponents from '../../custcomponents';
import MessageBase from './Message.base'
import SelectObject from '../../components/SelectObject'
import appLocaleName from '../../common/Locale.tool'

const { Option } = Select
const { RangePicker } = DatePicker
const { TextArea } = Input

const testValues = {};
/*
const testValues = {
  title: 'valentines day',
  status: 'UNREAD',
  senderId: 'P000001',
  receiverId: 'P000001',
  platformId: 'P000001',
  content: '    一段样例文字。\
可以分段。\
\
可以空行。\
\
',
}
*/


const imageKeys = [
]


class MessageAssociateForm extends Component {
  state = {
    previewVisible: false,
    previewImage: '',
    convertedImagesValues: {},
  }

  componentDidMount() {
 
    
    
    
  }

  handlePreview = (file) => {
    console.log('preview file', file)
    this.setState({
      previewImage: file.url || file.thumbUrl,
      previewVisible: true,
    })
  }

  



  handleChange = (event, source) => {
    console.log('get file list from change in update change:', source)

    const { fileList } = event
    const { convertedImagesValues } = this.state

    convertedImagesValues[source] = fileList
    this.setState({ convertedImagesValues })
    console.log('/get file list from change in update change:', source)
  }
	
  

  render() {
	const { form, dispatch, submitting, role,data,owner,toggleAssociatePaymentVisible,visible,onCancel, onCreate } = this.props
    const { convertedImagesValues } = this.state
    const {MessageService} = GlobalComponents
    const userContext = null
    


    const { getFieldDecorator, validateFieldsAndScroll, getFieldsError } = form
    const {fieldLabels} = MessageBase
    
    const capFirstChar = (value)=>{
    	//const upper = value.replace(/^\w/, c => c.toUpperCase());
  		const upper = value.charAt(0).toUpperCase() + value.substr(1);
  		return upper
  	}
    
    
    

    
    
    const tryinit  = (fieldName, candidates) => {
      
      if(candidates&&candidates.length==1){
          return candidates[0].id
      }
      const { owner } = this.props
      const { referenceName } = owner
      if(referenceName!=fieldName){
        return null
      }
      return owner.id
    }
    
    const availableForEdit= (fieldName) =>{
      const { owner } = this.props
      const { referenceName } = owner
      if(referenceName!=fieldName){
        return true
      }
      return false
    
    }
    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 12 },
    }
    const switchFormItemLayout = {
      labelCol: { span: 14 },
      wrapperCol: { span: 4 },
    }
   
    return (
 <Modal
          title={appLocaleName(userContext,"CreateNew")}
          visible={visible}
          onOk={onCancel}
          onCancel={onCancel}
          width={920}
          style={{ top: 40}}
        >
        <Card title={appLocaleName(userContext,"BasicInfo")}  className={styles.card} style={{ backgroundColor:"#eee" }}>
          <Form >
            <Row gutter={16}>

              <Col lg={12} md={12} sm={12}>
                <Form.Item label={fieldLabels.title} {...formItemLayout}>
                  {getFieldDecorator('title', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input placeholder="请输入Title" />
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={12}>
                <Form.Item label={fieldLabels.status} {...formItemLayout}>
                  {getFieldDecorator('status', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <Input placeholder="请输入Status" />
                  )}
                </Form.Item>
              </Col>

            </Row>


       
        





       
            <Row gutter={16}>
              <Col lg={24} md={24} sm={24}>
                <Form.Item>
                  {getFieldDecorator('content', {
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                    <TextArea rows={4} placeholder={appLocaleName(userContext,"PleaseInput")} />
                  )}
                </Form.Item>
              </Col>
      </Row>
        





       
            <Row gutter={16}>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.sender} {...formItemLayout}>
                  {getFieldDecorator('senderId', {
                  	initialValue: tryinit('sender'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('sender')}
                    targetType={"sender"} 
                    requestFunction={MessageService.requestCandidateSender}/>
  
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.receiver} {...formItemLayout}>
                  {getFieldDecorator('receiverId', {
                  	initialValue: tryinit('receiver'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('receiver')}
                    targetType={"receiver"} 
                    requestFunction={MessageService.requestCandidateReceiver}/>
  
                  )}
                </Form.Item>
              </Col>

              <Col lg={12} md={12} sm={24}>
                <Form.Item label={fieldLabels.platform} {...formItemLayout}>
                  {getFieldDecorator('platformId', {
                  	initialValue: tryinit('platform'),
                    rules: [{ required: true, message: appLocaleName(userContext,"PleaseInput") }],
                  })(
                <SelectObject 
                    disabled={!availableForEdit('platform')}
                    targetType={"platform"} 
                    requestFunction={MessageService.requestCandidatePlatform}/>
  
                  )}
                </Form.Item>
              </Col>

            </Row>
         
       

			</Form>
			
			
			
			
        </Card>
        
        
        
        
      </Modal>)
    
  }
}

export default connect(state => ({
  collapsed: state.global.collapsed,
}))(Form.create()(MessageAssociateForm))




