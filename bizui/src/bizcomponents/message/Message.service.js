import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}messageManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}messageManager/loadMessage/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateSender = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}messageManager/requestCandidateSender/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherSender = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}messageManager/transferToAnotherSender/id/anotherSenderId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateReceiver = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}messageManager/requestCandidateReceiver/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherReceiver = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}messageManager/transferToAnotherReceiver/id/anotherReceiverId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}messageManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}messageManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}






const MessageService = { view,
  load,
  requestCandidateSender,
  requestCandidateReceiver,
  requestCandidatePlatform,
  transferToAnotherSender,
  transferToAnotherReceiver,
  transferToAnotherPlatform }
export default MessageService

