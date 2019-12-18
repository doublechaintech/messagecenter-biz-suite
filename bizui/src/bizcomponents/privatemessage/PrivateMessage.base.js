import React from 'react'
import { Icon,Divider } from 'antd'

import { Link } from 'dva/router'
import moment from 'moment'
import ImagePreview from '../../components/ImagePreview'
import appLocaleName from '../../common/Locale.tool'
import BaseTool from '../../common/Base.tool'
import GlobalComponents from '../../custcomponents'
import DescriptionList from '../../components/DescriptionList'
const { Description } = DescriptionList
const {
	defaultRenderReferenceCell,
	defaultRenderBooleanCell,
	defaultRenderMoneyCell,
	defaultRenderDateTimeCell,
	defaultRenderImageCell,
	defaultRenderDateCell,
	defaultRenderIdentifier,
	defaultRenderTextCell,
} = BaseTool

const renderTextCell=defaultRenderTextCell
const renderIdentifier=defaultRenderIdentifier
const renderDateCell=defaultRenderDateCell
const renderDateTimeCell=defaultRenderDateTimeCell
const renderImageCell=defaultRenderImageCell
const renderMoneyCell=defaultRenderMoneyCell
const renderBooleanCell=defaultRenderBooleanCell
const renderReferenceCell=defaultRenderReferenceCell


const menuData = {menuName:"私信", menuFor: "privateMessage",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: 'ID',
  title: '标题',
  content: '内容',
  sendTime: '发送时间',
  readTime: '阅读时间',
  sender: '发送方',
  receiver: '收货人',
  status: '状态',
  platform: '平台',

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '20',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.title, debugtype: 'string', dataIndex: 'title', width: '18',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.content, debugtype: 'string_longtext', dataIndex: 'content', width: '10',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.sendTime, dataIndex: 'sendTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.readTime, dataIndex: 'readTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.sender, dataIndex: 'sender', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.receiver, dataIndex: 'receiver', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.status, debugtype: 'string', dataIndex: 'status', width: '10',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]
// refernce to https://ant.design/components/list-cn/
const renderItemOfList=(privateMessage,targetComponent)=>{

	
	
	
	const userContext = null
	return (
	<div key={privateMessage.id}>
	
	<DescriptionList  key={privateMessage.id} size="small" col="4">
<Description term="ID">{privateMessage.id}</Description> 
<Description term="标题">{privateMessage.title}</Description> 
<Description term="发送时间">{ moment(privateMessage.sendTime).format('YYYY-MM-DD')}</Description> 
<Description term="阅读时间">{ moment(privateMessage.readTime).format('YYYY-MM-DD')}</Description> 
<Description term="发送方">{privateMessage.sender==null?appLocaleName(userContext,"NotAssigned"):`${privateMessage.sender.displayName}(${privateMessage.sender.id})`}
</Description>
<Description term="收货人">{privateMessage.receiver==null?appLocaleName(userContext,"NotAssigned"):`${privateMessage.receiver.displayName}(${privateMessage.receiver.id})`}
</Description>
<Description term="状态">{privateMessage.status}</Description> 
	
        
      </DescriptionList>
       <Divider style={{ height: '2px' }} />
      </div>
	)

}
	



const PrivateMessageBase={menuData,displayColumns,fieldLabels,renderItemOfList}
export default PrivateMessageBase



