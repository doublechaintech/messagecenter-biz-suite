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


const menuData = {menuName:"平台", menuFor: "platform",
  		subItems: [
  {name: 'profileList', displayName:'配置文件', icon:'file',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'privateMessageList', displayName:'私信', icon:'at',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}

const fieldLabels = {
  id: 'ID',
  name: '名称',
  introduction: '介绍',
  currentVersion: '当前版本',

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '20', render: (text, record)=>renderTextCell(text,record,'platform') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '27',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.introduction, debugtype: 'string', dataIndex: 'introduction', width: '76',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.currentVersion, debugtype: 'string', dataIndex: 'currentVersion', width: '8',render: (text, record)=>renderTextCell(text,record)},

]
// refernce to https://ant.design/components/list-cn/
const renderItemOfList=(platform,targetComponent)=>{

	
	
	
	const userContext = null
	return (
	<div key={platform.id}>
	
	<DescriptionList  key={platform.id} size="small" col="4">
<Description term="ID">{platform.id}</Description> 
<Description term="名称">{platform.name}</Description> 
<Description term="介绍">{platform.introduction}</Description> 
<Description term="当前版本">{platform.currentVersion}</Description> 
	
        
      </DescriptionList>
       <Divider style={{ height: '2px' }} />
      </div>
	)

}
	



const PlatformBase={menuData,displayColumns,fieldLabels,renderItemOfList}
export default PlatformBase



