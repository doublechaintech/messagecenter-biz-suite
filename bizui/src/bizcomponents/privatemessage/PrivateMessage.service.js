import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}privateMessageManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}privateMessageManager/loadPrivateMessage/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateSender = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}privateMessageManager/requestCandidateSender/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherSender = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}privateMessageManager/transferToAnotherSender/id/anotherSenderId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateReceiver = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}privateMessageManager/requestCandidateReceiver/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherReceiver = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}privateMessageManager/transferToAnotherReceiver/id/anotherReceiverId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}privateMessageManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}privateMessageManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}






const PrivateMessageService = { view,
  load,
  requestCandidateSender,
  requestCandidateReceiver,
  requestCandidatePlatform,
  transferToAnotherSender,
  transferToAnotherReceiver,
  transferToAnotherPlatform }
export default PrivateMessageService

