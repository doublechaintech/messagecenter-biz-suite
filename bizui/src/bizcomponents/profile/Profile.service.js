import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}profileManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}profileManager/loadProfile/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}profileManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}profileManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addPrivateMessageAsSender = (targetObjectId, parameters) => {
  const url = `${PREFIX}profileManager/addPrivateMessageAsSender/profileId/title/content/status/platformId/tokensExpr/`
  const profileId = targetObjectId
  const requestParameters = { ...parameters, profileId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updatePrivateMessageAsSender = (targetObjectId, parameters) => {
  const url = `${PREFIX}profileManager/updatePrivateMessageAsSenderProperties/profileId/id/title/content/status/tokensExpr/`
  const profileId = targetObjectId
  const requestParameters = { ...parameters, profileId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removePrivateMessageListAsSender = (targetObjectId, parameters) => {
  const url = `${PREFIX}profileManager/removePrivateMessageListAsSender/profileId/privateMessageIds/tokensExpr/`
  const requestParameters = { ...parameters, profileId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addPrivateMessageAsReceiver = (targetObjectId, parameters) => {
  const url = `${PREFIX}profileManager/addPrivateMessageAsReceiver/profileId/title/content/status/platformId/tokensExpr/`
  const profileId = targetObjectId
  const requestParameters = { ...parameters, profileId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updatePrivateMessageAsReceiver = (targetObjectId, parameters) => {
  const url = `${PREFIX}profileManager/updatePrivateMessageAsReceiverProperties/profileId/id/title/content/status/tokensExpr/`
  const profileId = targetObjectId
  const requestParameters = { ...parameters, profileId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removePrivateMessageListAsReceiver = (targetObjectId, parameters) => {
  const url = `${PREFIX}profileManager/removePrivateMessageListAsReceiver/profileId/privateMessageIds/tokensExpr/`
  const requestParameters = { ...parameters, profileId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}


const ProfileService = { view,
  load,
  addPrivateMessageAsSender,
  addPrivateMessageAsReceiver,
  updatePrivateMessageAsSender,
  updatePrivateMessageAsReceiver,
  removePrivateMessageListAsSender,
  removePrivateMessageListAsReceiver,
  requestCandidatePlatform,
  transferToAnotherPlatform }
export default ProfileService

