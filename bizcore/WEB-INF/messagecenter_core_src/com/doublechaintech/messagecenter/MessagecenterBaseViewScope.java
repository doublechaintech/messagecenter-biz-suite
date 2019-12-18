package com.doublechaintech.messagecenter;


import com.terapico.caf.viewpage.SerializeScope;

import com.doublechaintech.messagecenter.platform.Platform;
import com.doublechaintech.messagecenter.profile.Profile;
import com.doublechaintech.messagecenter.privatemessage.PrivateMessage;
import com.doublechaintech.messagecenter.userdomain.UserDomain;
import com.doublechaintech.messagecenter.userwhitelist.UserWhiteList;
import com.doublechaintech.messagecenter.secuser.SecUser;
import com.doublechaintech.messagecenter.secuserblocking.SecUserBlocking;
import com.doublechaintech.messagecenter.userapp.UserApp;
import com.doublechaintech.messagecenter.listaccess.ListAccess;
import com.doublechaintech.messagecenter.objectaccess.ObjectAccess;
import com.doublechaintech.messagecenter.loginhistory.LoginHistory;
import com.doublechaintech.messagecenter.genericform.GenericForm;
import com.doublechaintech.messagecenter.formmessage.FormMessage;
import com.doublechaintech.messagecenter.formfieldmessage.FormFieldMessage;
import com.doublechaintech.messagecenter.formfield.FormField;
import com.doublechaintech.messagecenter.formaction.FormAction;


public class MessagecenterBaseViewScope {

	protected static SerializeScope PlatformBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.INTRODUCTION_PROPERTY)
		.field(Platform.CURRENT_VERSION_PROPERTY)
		;
	/** 用于Platform的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPlatformSummaryScope() {
		return PlatformBaseSummaryScope;
	}

	protected static SerializeScope ProfileBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(Profile.ID_PROPERTY)
		.field(Profile.NAME_PROPERTY)
		;
	/** 用于Profile的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getProfileSummaryScope() {
		return ProfileBaseSummaryScope;
	}

	protected static SerializeScope PrivateMessageBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(PrivateMessage.ID_PROPERTY)
		.field(PrivateMessage.TITLE_PROPERTY)
		.field(PrivateMessage.CONTENT_PROPERTY)
		.field(PrivateMessage.SEND_TIME_PROPERTY)
		.field(PrivateMessage.READ_TIME_PROPERTY)
		.field(PrivateMessage.STATUS_PROPERTY)
		;
	/** 用于PrivateMessage的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPrivateMessageSummaryScope() {
		return PrivateMessageBaseSummaryScope;
	}

	protected static SerializeScope UserDomainBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		;
	/** 用于UserDomain的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserDomainSummaryScope() {
		return UserDomainBaseSummaryScope;
	}

	protected static SerializeScope UserWhiteListBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		;
	/** 用于UserWhiteList的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListSummaryScope() {
		return UserWhiteListBaseSummaryScope;
	}

	protected static SerializeScope SecUserBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		.field(SecUser.CURRENT_STATUS_PROPERTY)
		;
	/** 用于SecUser的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSecUserSummaryScope() {
		return SecUserBaseSummaryScope;
	}

	protected static SerializeScope SecUserBlockingBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(SecUserBlocking.ID_PROPERTY)
		.field(SecUserBlocking.WHO_PROPERTY)
		.field(SecUserBlocking.BLOCK_TIME_PROPERTY)
		.field(SecUserBlocking.COMMENTS_PROPERTY)
		;
	/** 用于SecUserBlocking的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSecUserBlockingSummaryScope() {
		return SecUserBlockingBaseSummaryScope;
	}

	protected static SerializeScope UserAppBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		;
	/** 用于UserApp的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserAppSummaryScope() {
		return UserAppBaseSummaryScope;
	}

	protected static SerializeScope ListAccessBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		;
	/** 用于ListAccess的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getListAccessSummaryScope() {
		return ListAccessBaseSummaryScope;
	}

	protected static SerializeScope ObjectAccessBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(ObjectAccess.ID_PROPERTY)
		.field(ObjectAccess.NAME_PROPERTY)
		.field(ObjectAccess.OBJECT_TYPE_PROPERTY)
		.field(ObjectAccess.LIST1_PROPERTY)
		.field(ObjectAccess.LIST2_PROPERTY)
		.field(ObjectAccess.LIST3_PROPERTY)
		.field(ObjectAccess.LIST4_PROPERTY)
		.field(ObjectAccess.LIST5_PROPERTY)
		.field(ObjectAccess.LIST6_PROPERTY)
		.field(ObjectAccess.LIST7_PROPERTY)
		.field(ObjectAccess.LIST8_PROPERTY)
		.field(ObjectAccess.LIST9_PROPERTY)
		;
	/** 用于ObjectAccess的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getObjectAccessSummaryScope() {
		return ObjectAccessBaseSummaryScope;
	}

	protected static SerializeScope LoginHistoryBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		;
	/** 用于LoginHistory的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getLoginHistorySummaryScope() {
		return LoginHistoryBaseSummaryScope;
	}

	protected static SerializeScope GenericFormBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(GenericForm.ID_PROPERTY)
		.field(GenericForm.TITLE_PROPERTY)
		.field(GenericForm.DESCRIPTION_PROPERTY)
		;
	/** 用于GenericForm的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getGenericFormSummaryScope() {
		return GenericFormBaseSummaryScope;
	}

	protected static SerializeScope FormMessageBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormMessage.ID_PROPERTY)
		.field(FormMessage.TITLE_PROPERTY)
		.field(FormMessage.LEVEL_PROPERTY)
		;
	/** 用于FormMessage的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormMessageSummaryScope() {
		return FormMessageBaseSummaryScope;
	}

	protected static SerializeScope FormFieldMessageBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormFieldMessage.ID_PROPERTY)
		.field(FormFieldMessage.TITLE_PROPERTY)
		.field(FormFieldMessage.PARAMETER_NAME_PROPERTY)
		.field(FormFieldMessage.LEVEL_PROPERTY)
		;
	/** 用于FormFieldMessage的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormFieldMessageSummaryScope() {
		return FormFieldMessageBaseSummaryScope;
	}

	protected static SerializeScope FormFieldBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormField.ID_PROPERTY)
		.field(FormField.LABEL_PROPERTY)
		.field(FormField.LOCALE_KEY_PROPERTY)
		.field(FormField.PARAMETER_NAME_PROPERTY)
		.field(FormField.TYPE_PROPERTY)
		.field(FormField.PLACEHOLDER_PROPERTY)
		.field(FormField.DEFAULT_VALUE_PROPERTY)
		.field(FormField.DESCRIPTION_PROPERTY)
		.field(FormField.FIELD_GROUP_PROPERTY)
		.field(FormField.MINIMUM_VALUE_PROPERTY)
		.field(FormField.MAXIMUM_VALUE_PROPERTY)
		.field(FormField.REQUIRED_PROPERTY)
		.field(FormField.DISABLED_PROPERTY)
		.field(FormField.CUSTOM_RENDERING_PROPERTY)
		.field(FormField.CANDIDATE_VALUES_PROPERTY)
		.field(FormField.SUGGEST_VALUES_PROPERTY)
		;
	/** 用于FormField的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormFieldSummaryScope() {
		return FormFieldBaseSummaryScope;
	}

	protected static SerializeScope FormActionBaseSummaryScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormAction.ID_PROPERTY)
		.field(FormAction.LABEL_PROPERTY)
		.field(FormAction.LOCALE_KEY_PROPERTY)
		.field(FormAction.ACTION_KEY_PROPERTY)
		.field(FormAction.LEVEL_PROPERTY)
		.field(FormAction.URL_PROPERTY)
		;
	/** 用于FormAction的子对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormActionSummaryScope() {
		return FormActionBaseSummaryScope;
	}

	protected static SerializeScope PlatformBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.INTRODUCTION_PROPERTY)
		.field(Platform.CURRENT_VERSION_PROPERTY)
		;
	/** 用于Platform的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPlatformSecondaryListItemScope() {
		return PlatformBaseSecondaryListItemScope;
	}

	protected static SerializeScope ProfileBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(Profile.ID_PROPERTY)
		.field(Profile.NAME_PROPERTY)
		;
	/** 用于Profile的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getProfileSecondaryListItemScope() {
		return ProfileBaseSecondaryListItemScope;
	}

	protected static SerializeScope PrivateMessageBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(PrivateMessage.ID_PROPERTY)
		.field(PrivateMessage.TITLE_PROPERTY)
		.field(PrivateMessage.CONTENT_PROPERTY)
		.field(PrivateMessage.SEND_TIME_PROPERTY)
		.field(PrivateMessage.READ_TIME_PROPERTY)
		.field(PrivateMessage.STATUS_PROPERTY)
		;
	/** 用于PrivateMessage的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPrivateMessageSecondaryListItemScope() {
		return PrivateMessageBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserDomainBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		;
	/** 用于UserDomain的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserDomainSecondaryListItemScope() {
		return UserDomainBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserWhiteListBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		;
	/** 用于UserWhiteList的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListSecondaryListItemScope() {
		return UserWhiteListBaseSecondaryListItemScope;
	}

	protected static SerializeScope SecUserBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		.field(SecUser.CURRENT_STATUS_PROPERTY)
		;
	/** 用于SecUser的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSecUserSecondaryListItemScope() {
		return SecUserBaseSecondaryListItemScope;
	}

	protected static SerializeScope SecUserBlockingBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(SecUserBlocking.ID_PROPERTY)
		.field(SecUserBlocking.WHO_PROPERTY)
		.field(SecUserBlocking.BLOCK_TIME_PROPERTY)
		.field(SecUserBlocking.COMMENTS_PROPERTY)
		;
	/** 用于SecUserBlocking的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSecUserBlockingSecondaryListItemScope() {
		return SecUserBlockingBaseSecondaryListItemScope;
	}

	protected static SerializeScope UserAppBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		;
	/** 用于UserApp的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserAppSecondaryListItemScope() {
		return UserAppBaseSecondaryListItemScope;
	}

	protected static SerializeScope ListAccessBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		;
	/** 用于ListAccess的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getListAccessSecondaryListItemScope() {
		return ListAccessBaseSecondaryListItemScope;
	}

	protected static SerializeScope ObjectAccessBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(ObjectAccess.ID_PROPERTY)
		.field(ObjectAccess.NAME_PROPERTY)
		.field(ObjectAccess.OBJECT_TYPE_PROPERTY)
		.field(ObjectAccess.LIST1_PROPERTY)
		.field(ObjectAccess.LIST2_PROPERTY)
		.field(ObjectAccess.LIST3_PROPERTY)
		.field(ObjectAccess.LIST4_PROPERTY)
		.field(ObjectAccess.LIST5_PROPERTY)
		.field(ObjectAccess.LIST6_PROPERTY)
		.field(ObjectAccess.LIST7_PROPERTY)
		.field(ObjectAccess.LIST8_PROPERTY)
		.field(ObjectAccess.LIST9_PROPERTY)
		;
	/** 用于ObjectAccess的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getObjectAccessSecondaryListItemScope() {
		return ObjectAccessBaseSecondaryListItemScope;
	}

	protected static SerializeScope LoginHistoryBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		;
	/** 用于LoginHistory的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getLoginHistorySecondaryListItemScope() {
		return LoginHistoryBaseSecondaryListItemScope;
	}

	protected static SerializeScope GenericFormBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(GenericForm.ID_PROPERTY)
		.field(GenericForm.TITLE_PROPERTY)
		.field(GenericForm.DESCRIPTION_PROPERTY)
		;
	/** 用于GenericForm的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getGenericFormSecondaryListItemScope() {
		return GenericFormBaseSecondaryListItemScope;
	}

	protected static SerializeScope FormMessageBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormMessage.ID_PROPERTY)
		.field(FormMessage.TITLE_PROPERTY)
		.field(FormMessage.LEVEL_PROPERTY)
		;
	/** 用于FormMessage的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormMessageSecondaryListItemScope() {
		return FormMessageBaseSecondaryListItemScope;
	}

	protected static SerializeScope FormFieldMessageBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormFieldMessage.ID_PROPERTY)
		.field(FormFieldMessage.TITLE_PROPERTY)
		.field(FormFieldMessage.PARAMETER_NAME_PROPERTY)
		.field(FormFieldMessage.LEVEL_PROPERTY)
		;
	/** 用于FormFieldMessage的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormFieldMessageSecondaryListItemScope() {
		return FormFieldMessageBaseSecondaryListItemScope;
	}

	protected static SerializeScope FormFieldBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormField.ID_PROPERTY)
		.field(FormField.LABEL_PROPERTY)
		.field(FormField.LOCALE_KEY_PROPERTY)
		.field(FormField.PARAMETER_NAME_PROPERTY)
		.field(FormField.TYPE_PROPERTY)
		.field(FormField.PLACEHOLDER_PROPERTY)
		.field(FormField.DEFAULT_VALUE_PROPERTY)
		.field(FormField.DESCRIPTION_PROPERTY)
		.field(FormField.FIELD_GROUP_PROPERTY)
		.field(FormField.MINIMUM_VALUE_PROPERTY)
		.field(FormField.MAXIMUM_VALUE_PROPERTY)
		.field(FormField.REQUIRED_PROPERTY)
		.field(FormField.DISABLED_PROPERTY)
		.field(FormField.CUSTOM_RENDERING_PROPERTY)
		.field(FormField.CANDIDATE_VALUES_PROPERTY)
		.field(FormField.SUGGEST_VALUES_PROPERTY)
		;
	/** 用于FormField的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormFieldSecondaryListItemScope() {
		return FormFieldBaseSecondaryListItemScope;
	}

	protected static SerializeScope FormActionBaseSecondaryListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormAction.ID_PROPERTY)
		.field(FormAction.LABEL_PROPERTY)
		.field(FormAction.LOCALE_KEY_PROPERTY)
		.field(FormAction.ACTION_KEY_PROPERTY)
		.field(FormAction.LEVEL_PROPERTY)
		.field(FormAction.URL_PROPERTY)
		;
	/** 用于FormAction的父对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormActionSecondaryListItemScope() {
		return FormActionBaseSecondaryListItemScope;
	}

	protected static SerializeScope PlatformBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.INTRODUCTION_PROPERTY)
		.field(Platform.CURRENT_VERSION_PROPERTY)
		.field(Platform.PROFILE_LIST, getProfileSecondaryListItemScope())
		.field(Platform.PRIVATE_MESSAGE_LIST, getPrivateMessageSecondaryListItemScope())
		;
	/** 用于Platform对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPlatformListItemScope() {
		return PlatformBaseListItemScope;
	}

	protected static SerializeScope ProfileBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(Profile.ID_PROPERTY)
		.field(Profile.NAME_PROPERTY)
		.field(Profile.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(Profile.PRIVATE_MESSAGE_LIST_AS_SENDER, getPrivateMessageSecondaryListItemScope())
		.field(Profile.PRIVATE_MESSAGE_LIST_AS_RECEIVER, getPrivateMessageSecondaryListItemScope())
		;
	/** 用于Profile对象的列表时需要序列化的属性列表 */
	public static SerializeScope getProfileListItemScope() {
		return ProfileBaseListItemScope;
	}

	protected static SerializeScope PrivateMessageBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(PrivateMessage.ID_PROPERTY)
		.field(PrivateMessage.TITLE_PROPERTY)
		.field(PrivateMessage.CONTENT_PROPERTY)
		.field(PrivateMessage.SEND_TIME_PROPERTY)
		.field(PrivateMessage.READ_TIME_PROPERTY)
		.field(PrivateMessage.SENDER_PROPERTY, getProfileSummaryScope())
		.field(PrivateMessage.RECEIVER_PROPERTY, getProfileSummaryScope())
		.field(PrivateMessage.STATUS_PROPERTY)
		.field(PrivateMessage.PLATFORM_PROPERTY, getPlatformSummaryScope())
		;
	/** 用于PrivateMessage对象的列表时需要序列化的属性列表 */
	public static SerializeScope getPrivateMessageListItemScope() {
		return PrivateMessageBaseListItemScope;
	}

	protected static SerializeScope UserDomainBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		.field(UserDomain.USER_WHITE_LIST_LIST, getUserWhiteListSecondaryListItemScope())
		.field(UserDomain.SEC_USER_LIST, getSecUserSecondaryListItemScope())
		;
	/** 用于UserDomain对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserDomainListItemScope() {
		return UserDomainBaseListItemScope;
	}

	protected static SerializeScope UserWhiteListBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		.field(UserWhiteList.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		;
	/** 用于UserWhiteList对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListListItemScope() {
		return UserWhiteListBaseListItemScope;
	}

	protected static SerializeScope SecUserBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		.field(SecUser.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		.field(SecUser.BLOCKING_PROPERTY, getSecUserBlockingSummaryScope())
		.field(SecUser.CURRENT_STATUS_PROPERTY)
		.field(SecUser.USER_APP_LIST, getUserAppSecondaryListItemScope())
		.field(SecUser.LOGIN_HISTORY_LIST, getLoginHistorySecondaryListItemScope())
		;
	/** 用于SecUser对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSecUserListItemScope() {
		return SecUserBaseListItemScope;
	}

	protected static SerializeScope SecUserBlockingBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(SecUserBlocking.ID_PROPERTY)
		.field(SecUserBlocking.WHO_PROPERTY)
		.field(SecUserBlocking.BLOCK_TIME_PROPERTY)
		.field(SecUserBlocking.COMMENTS_PROPERTY)
		.field(SecUserBlocking.SEC_USER_LIST, getSecUserSecondaryListItemScope())
		;
	/** 用于SecUserBlocking对象的列表时需要序列化的属性列表 */
	public static SerializeScope getSecUserBlockingListItemScope() {
		return SecUserBlockingBaseListItemScope;
	}

	protected static SerializeScope UserAppBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		.field(UserApp.LIST_ACCESS_LIST, getListAccessSecondaryListItemScope())
		.field(UserApp.OBJECT_ACCESS_LIST, getObjectAccessSecondaryListItemScope())
		;
	/** 用于UserApp对象的列表时需要序列化的属性列表 */
	public static SerializeScope getUserAppListItemScope() {
		return UserAppBaseListItemScope;
	}

	protected static SerializeScope ListAccessBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		.field(ListAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ListAccess对象的列表时需要序列化的属性列表 */
	public static SerializeScope getListAccessListItemScope() {
		return ListAccessBaseListItemScope;
	}

	protected static SerializeScope ObjectAccessBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(ObjectAccess.ID_PROPERTY)
		.field(ObjectAccess.NAME_PROPERTY)
		.field(ObjectAccess.OBJECT_TYPE_PROPERTY)
		.field(ObjectAccess.LIST1_PROPERTY)
		.field(ObjectAccess.LIST2_PROPERTY)
		.field(ObjectAccess.LIST3_PROPERTY)
		.field(ObjectAccess.LIST4_PROPERTY)
		.field(ObjectAccess.LIST5_PROPERTY)
		.field(ObjectAccess.LIST6_PROPERTY)
		.field(ObjectAccess.LIST7_PROPERTY)
		.field(ObjectAccess.LIST8_PROPERTY)
		.field(ObjectAccess.LIST9_PROPERTY)
		.field(ObjectAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ObjectAccess对象的列表时需要序列化的属性列表 */
	public static SerializeScope getObjectAccessListItemScope() {
		return ObjectAccessBaseListItemScope;
	}

	protected static SerializeScope LoginHistoryBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		.field(LoginHistory.SEC_USER_PROPERTY, getSecUserSummaryScope())
		;
	/** 用于LoginHistory对象的列表时需要序列化的属性列表 */
	public static SerializeScope getLoginHistoryListItemScope() {
		return LoginHistoryBaseListItemScope;
	}

	protected static SerializeScope GenericFormBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(GenericForm.ID_PROPERTY)
		.field(GenericForm.TITLE_PROPERTY)
		.field(GenericForm.DESCRIPTION_PROPERTY)
		.field(GenericForm.FORM_MESSAGE_LIST, getFormMessageSecondaryListItemScope())
		.field(GenericForm.FORM_FIELD_MESSAGE_LIST, getFormFieldMessageSecondaryListItemScope())
		.field(GenericForm.FORM_FIELD_LIST, getFormFieldSecondaryListItemScope())
		.field(GenericForm.FORM_ACTION_LIST, getFormActionSecondaryListItemScope())
		;
	/** 用于GenericForm对象的列表时需要序列化的属性列表 */
	public static SerializeScope getGenericFormListItemScope() {
		return GenericFormBaseListItemScope;
	}

	protected static SerializeScope FormMessageBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormMessage.ID_PROPERTY)
		.field(FormMessage.TITLE_PROPERTY)
		.field(FormMessage.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormMessage.LEVEL_PROPERTY)
		;
	/** 用于FormMessage对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormMessageListItemScope() {
		return FormMessageBaseListItemScope;
	}

	protected static SerializeScope FormFieldMessageBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormFieldMessage.ID_PROPERTY)
		.field(FormFieldMessage.TITLE_PROPERTY)
		.field(FormFieldMessage.PARAMETER_NAME_PROPERTY)
		.field(FormFieldMessage.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormFieldMessage.LEVEL_PROPERTY)
		;
	/** 用于FormFieldMessage对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormFieldMessageListItemScope() {
		return FormFieldMessageBaseListItemScope;
	}

	protected static SerializeScope FormFieldBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormField.ID_PROPERTY)
		.field(FormField.LABEL_PROPERTY)
		.field(FormField.LOCALE_KEY_PROPERTY)
		.field(FormField.PARAMETER_NAME_PROPERTY)
		.field(FormField.TYPE_PROPERTY)
		.field(FormField.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormField.PLACEHOLDER_PROPERTY)
		.field(FormField.DEFAULT_VALUE_PROPERTY)
		.field(FormField.DESCRIPTION_PROPERTY)
		.field(FormField.FIELD_GROUP_PROPERTY)
		.field(FormField.MINIMUM_VALUE_PROPERTY)
		.field(FormField.MAXIMUM_VALUE_PROPERTY)
		.field(FormField.REQUIRED_PROPERTY)
		.field(FormField.DISABLED_PROPERTY)
		.field(FormField.CUSTOM_RENDERING_PROPERTY)
		.field(FormField.CANDIDATE_VALUES_PROPERTY)
		.field(FormField.SUGGEST_VALUES_PROPERTY)
		;
	/** 用于FormField对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormFieldListItemScope() {
		return FormFieldBaseListItemScope;
	}

	protected static SerializeScope FormActionBaseListItemScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormAction.ID_PROPERTY)
		.field(FormAction.LABEL_PROPERTY)
		.field(FormAction.LOCALE_KEY_PROPERTY)
		.field(FormAction.ACTION_KEY_PROPERTY)
		.field(FormAction.LEVEL_PROPERTY)
		.field(FormAction.URL_PROPERTY)
		.field(FormAction.FORM_PROPERTY, getGenericFormSummaryScope())
		;
	/** 用于FormAction对象的列表时需要序列化的属性列表 */
	public static SerializeScope getFormActionListItemScope() {
		return FormActionBaseListItemScope;
	}

	protected static SerializeScope PlatformBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(Platform.ID_PROPERTY)
		.field(Platform.NAME_PROPERTY)
		.field(Platform.INTRODUCTION_PROPERTY)
		.field(Platform.CURRENT_VERSION_PROPERTY)
		.field(Platform.PROFILE_LIST, getProfileListItemScope())
		.field(Platform.PRIVATE_MESSAGE_LIST, getPrivateMessageListItemScope())
		;
	/** 用于Platform对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPlatformDetailScope() {
		return PlatformBaseDetailScope;
	}

	protected static SerializeScope ProfileBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(Profile.ID_PROPERTY)
		.field(Profile.NAME_PROPERTY)
		.field(Profile.PLATFORM_PROPERTY, getPlatformSummaryScope())
		.field(Profile.PRIVATE_MESSAGE_LIST_AS_SENDER, getPrivateMessageListItemScope())
		.field(Profile.PRIVATE_MESSAGE_LIST_AS_RECEIVER, getPrivateMessageListItemScope())
		;
	/** 用于Profile对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getProfileDetailScope() {
		return ProfileBaseDetailScope;
	}

	protected static SerializeScope PrivateMessageBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(PrivateMessage.ID_PROPERTY)
		.field(PrivateMessage.TITLE_PROPERTY)
		.field(PrivateMessage.CONTENT_PROPERTY)
		.field(PrivateMessage.SEND_TIME_PROPERTY)
		.field(PrivateMessage.READ_TIME_PROPERTY)
		.field(PrivateMessage.SENDER_PROPERTY, getProfileSummaryScope())
		.field(PrivateMessage.RECEIVER_PROPERTY, getProfileSummaryScope())
		.field(PrivateMessage.STATUS_PROPERTY)
		.field(PrivateMessage.PLATFORM_PROPERTY, getPlatformSummaryScope())
		;
	/** 用于PrivateMessage对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getPrivateMessageDetailScope() {
		return PrivateMessageBaseDetailScope;
	}

	protected static SerializeScope UserDomainBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(UserDomain.ID_PROPERTY)
		.field(UserDomain.NAME_PROPERTY)
		.field(UserDomain.USER_WHITE_LIST_LIST, getUserWhiteListListItemScope())
		.field(UserDomain.SEC_USER_LIST, getSecUserListItemScope())
		;
	/** 用于UserDomain对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserDomainDetailScope() {
		return UserDomainBaseDetailScope;
	}

	protected static SerializeScope UserWhiteListBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(UserWhiteList.ID_PROPERTY)
		.field(UserWhiteList.USER_IDENTITY_PROPERTY)
		.field(UserWhiteList.USER_SPECIAL_FUNCTIONS_PROPERTY)
		.field(UserWhiteList.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		;
	/** 用于UserWhiteList对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserWhiteListDetailScope() {
		return UserWhiteListBaseDetailScope;
	}

	protected static SerializeScope SecUserBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(SecUser.ID_PROPERTY)
		.field(SecUser.LOGIN_PROPERTY)
		.field(SecUser.MOBILE_PROPERTY)
		.field(SecUser.EMAIL_PROPERTY)
		.field(SecUser.PWD_PROPERTY)
		.field(SecUser.WEIXIN_OPENID_PROPERTY)
		.field(SecUser.WEIXIN_APPID_PROPERTY)
		.field(SecUser.ACCESS_TOKEN_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_PROPERTY)
		.field(SecUser.VERIFICATION_CODE_EXPIRE_PROPERTY)
		.field(SecUser.LAST_LOGIN_TIME_PROPERTY)
		.field(SecUser.DOMAIN_PROPERTY, getUserDomainSummaryScope())
		.field(SecUser.BLOCKING_PROPERTY, getSecUserBlockingSummaryScope())
		.field(SecUser.CURRENT_STATUS_PROPERTY)
		.field(SecUser.USER_APP_LIST, getUserAppListItemScope())
		.field(SecUser.LOGIN_HISTORY_LIST, getLoginHistoryListItemScope())
		;
	/** 用于SecUser对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSecUserDetailScope() {
		return SecUserBaseDetailScope;
	}

	protected static SerializeScope SecUserBlockingBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(SecUserBlocking.ID_PROPERTY)
		.field(SecUserBlocking.WHO_PROPERTY)
		.field(SecUserBlocking.BLOCK_TIME_PROPERTY)
		.field(SecUserBlocking.COMMENTS_PROPERTY)
		.field(SecUserBlocking.SEC_USER_LIST, getSecUserListItemScope())
		;
	/** 用于SecUserBlocking对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getSecUserBlockingDetailScope() {
		return SecUserBlockingBaseDetailScope;
	}

	protected static SerializeScope UserAppBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(UserApp.ID_PROPERTY)
		.field(UserApp.TITLE_PROPERTY)
		.field(UserApp.SEC_USER_PROPERTY, getSecUserSummaryScope())
		.field(UserApp.APP_ICON_PROPERTY)
		.field(UserApp.FULL_ACCESS_PROPERTY)
		.field(UserApp.PERMISSION_PROPERTY)
		.field(UserApp.OBJECT_TYPE_PROPERTY)
		.field(UserApp.OBJECT_ID_PROPERTY)
		.field(UserApp.LOCATION_PROPERTY)
		.field(UserApp.LIST_ACCESS_LIST, getListAccessListItemScope())
		.field(UserApp.OBJECT_ACCESS_LIST, getObjectAccessListItemScope())
		;
	/** 用于UserApp对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getUserAppDetailScope() {
		return UserAppBaseDetailScope;
	}

	protected static SerializeScope ListAccessBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(ListAccess.ID_PROPERTY)
		.field(ListAccess.NAME_PROPERTY)
		.field(ListAccess.INTERNAL_NAME_PROPERTY)
		.field(ListAccess.READ_PERMISSION_PROPERTY)
		.field(ListAccess.CREATE_PERMISSION_PROPERTY)
		.field(ListAccess.DELETE_PERMISSION_PROPERTY)
		.field(ListAccess.UPDATE_PERMISSION_PROPERTY)
		.field(ListAccess.EXECUTION_PERMISSION_PROPERTY)
		.field(ListAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ListAccess对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getListAccessDetailScope() {
		return ListAccessBaseDetailScope;
	}

	protected static SerializeScope ObjectAccessBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(ObjectAccess.ID_PROPERTY)
		.field(ObjectAccess.NAME_PROPERTY)
		.field(ObjectAccess.OBJECT_TYPE_PROPERTY)
		.field(ObjectAccess.LIST1_PROPERTY)
		.field(ObjectAccess.LIST2_PROPERTY)
		.field(ObjectAccess.LIST3_PROPERTY)
		.field(ObjectAccess.LIST4_PROPERTY)
		.field(ObjectAccess.LIST5_PROPERTY)
		.field(ObjectAccess.LIST6_PROPERTY)
		.field(ObjectAccess.LIST7_PROPERTY)
		.field(ObjectAccess.LIST8_PROPERTY)
		.field(ObjectAccess.LIST9_PROPERTY)
		.field(ObjectAccess.APP_PROPERTY, getUserAppSummaryScope())
		;
	/** 用于ObjectAccess对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getObjectAccessDetailScope() {
		return ObjectAccessBaseDetailScope;
	}

	protected static SerializeScope LoginHistoryBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(LoginHistory.ID_PROPERTY)
		.field(LoginHistory.LOGIN_TIME_PROPERTY)
		.field(LoginHistory.FROM_IP_PROPERTY)
		.field(LoginHistory.DESCRIPTION_PROPERTY)
		.field(LoginHistory.SEC_USER_PROPERTY, getSecUserSummaryScope())
		;
	/** 用于LoginHistory对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getLoginHistoryDetailScope() {
		return LoginHistoryBaseDetailScope;
	}

	protected static SerializeScope GenericFormBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(GenericForm.ID_PROPERTY)
		.field(GenericForm.TITLE_PROPERTY)
		.field(GenericForm.DESCRIPTION_PROPERTY)
		.field(GenericForm.FORM_MESSAGE_LIST, getFormMessageListItemScope())
		.field(GenericForm.FORM_FIELD_MESSAGE_LIST, getFormFieldMessageListItemScope())
		.field(GenericForm.FORM_FIELD_LIST, getFormFieldListItemScope())
		.field(GenericForm.FORM_ACTION_LIST, getFormActionListItemScope())
		;
	/** 用于GenericForm对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getGenericFormDetailScope() {
		return GenericFormBaseDetailScope;
	}

	protected static SerializeScope FormMessageBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormMessage.ID_PROPERTY)
		.field(FormMessage.TITLE_PROPERTY)
		.field(FormMessage.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormMessage.LEVEL_PROPERTY)
		;
	/** 用于FormMessage对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormMessageDetailScope() {
		return FormMessageBaseDetailScope;
	}

	protected static SerializeScope FormFieldMessageBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormFieldMessage.ID_PROPERTY)
		.field(FormFieldMessage.TITLE_PROPERTY)
		.field(FormFieldMessage.PARAMETER_NAME_PROPERTY)
		.field(FormFieldMessage.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormFieldMessage.LEVEL_PROPERTY)
		;
	/** 用于FormFieldMessage对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormFieldMessageDetailScope() {
		return FormFieldMessageBaseDetailScope;
	}

	protected static SerializeScope FormFieldBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormField.ID_PROPERTY)
		.field(FormField.LABEL_PROPERTY)
		.field(FormField.LOCALE_KEY_PROPERTY)
		.field(FormField.PARAMETER_NAME_PROPERTY)
		.field(FormField.TYPE_PROPERTY)
		.field(FormField.FORM_PROPERTY, getGenericFormSummaryScope())
		.field(FormField.PLACEHOLDER_PROPERTY)
		.field(FormField.DEFAULT_VALUE_PROPERTY)
		.field(FormField.DESCRIPTION_PROPERTY)
		.field(FormField.FIELD_GROUP_PROPERTY)
		.field(FormField.MINIMUM_VALUE_PROPERTY)
		.field(FormField.MAXIMUM_VALUE_PROPERTY)
		.field(FormField.REQUIRED_PROPERTY)
		.field(FormField.DISABLED_PROPERTY)
		.field(FormField.CUSTOM_RENDERING_PROPERTY)
		.field(FormField.CANDIDATE_VALUES_PROPERTY)
		.field(FormField.SUGGEST_VALUES_PROPERTY)
		;
	/** 用于FormField对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormFieldDetailScope() {
		return FormFieldBaseDetailScope;
	}

	protected static SerializeScope FormActionBaseDetailScope = SerializeScope.INCLUDE()
		.field(MessagecenterBaseConstants.X_LINK_TO_URL)
		.field(FormAction.ID_PROPERTY)
		.field(FormAction.LABEL_PROPERTY)
		.field(FormAction.LOCALE_KEY_PROPERTY)
		.field(FormAction.ACTION_KEY_PROPERTY)
		.field(FormAction.LEVEL_PROPERTY)
		.field(FormAction.URL_PROPERTY)
		.field(FormAction.FORM_PROPERTY, getGenericFormSummaryScope())
		;
	/** 用于FormAction对象的详情页时需要序列化的属性列表 */
	public static SerializeScope getFormActionDetailScope() {
		return FormActionBaseDetailScope;
	}

	

}






