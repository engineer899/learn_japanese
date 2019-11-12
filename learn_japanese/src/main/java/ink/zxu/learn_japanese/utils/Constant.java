package ink.zxu.learn_japanese.utils;

import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

/**
 * 系统常量
 * 
 * @ClassName: Constant
 * @Description: 系统启动后自动将参数加载到常量中
 * @author XWang
 * @date 2016年6月13日 下午5:05:25
 *
 */
public class Constant
{

	/***************** 系统配置常量设置 *****************/
	public static String SYS_DFC_PROVINCESHORTCODE = "";
	public static String SYS_DFC_PROVINCECODE = "";
	public static String SYS_DFC_PROVINCECODENAME = "";
	public static String SYS_DFC_MAPPOINTX = "";
	public static String SYS_DFC_MAPPOINTY = "";
	public static String SMSCONTENT = "";
	public static String SMSSWITCH = "";
	public static String SMSCOUNT = "";
	public static String SMSUSERID = "";
	public static String SMSACCOUNT = "";
	public static String SMSPASSWORD = "";
	public static String SMSURL = "";
	public static String SMSACTION = "";
	public static String INTERVALTIME = "";
	public static String MAINTOPIMG = "";
	public static String MULTIQUERYURL = "";
	public static String LOGINCOUNT = "";
	public static String SMSURI = "";
	public static String SMSKEY = "";
	public static String SMSSECRET = "";
	public static String SMSFREESIGNNAME = "";
	public static String SMSTEMPLATECODE = "";
	public static String SMSTUNNEL = "";
	public static String SMSURIL = "";
	public static String SMSUSER = "";
	public static String SMSPWD = "";
	public static String SMSCONTEXT = "";
	public static String YEARBEGIN="";
	
	public static String BACK_URL="";
	public static String VIDEO_NAME="";
	public static String VIDEO_PWD="";
	public static String VIDEO_IP="";
	public static String VIDEO_PORT="";
	public static String VIDEO_NUM="";
	
	public static String getYEARBEGIN() {
		return YEARBEGIN;
	}

	public static void setYEARBEGIN(String yEARBEGIN) {
		YEARBEGIN = yEARBEGIN;
	}

	public static String getSMSURIL()
	{
		return SMSURIL;
	}

	public static void setSMSURIL(String sMSURIL)
	{
		SMSURIL = sMSURIL;
	}

	public static String getSMSUSER()
	{
		return SMSUSER;
	}

	public static void setSMSUSER(String sMSUSER)
	{
		SMSUSER = sMSUSER;
	}

	public static String getSMSPWD()
	{
		return SMSPWD;
	}

	public static void setSMSPWD(String sMSPWD)
	{
		SMSPWD = sMSPWD;
	}

	public static String getSMSCONTEXT()
	{
		return SMSCONTEXT;
	}

	public static void setSMSCONTEXT(String sMSCONTEXT)
	{
		SMSCONTEXT = sMSCONTEXT;
	}


	public static String getSMSTUNNEL()
	{
		return SMSTUNNEL;
	}

	public static void setSMSTUNNEL(String sMSTUNNEL)
	{
		SMSTUNNEL = sMSTUNNEL;
	}

	public static String getSMSURI()
	{
		return SMSURI;
	}

	public static void setSMSURI(String sMSURI)
	{
		SMSURI = sMSURI;
	}

	public static String getSMSKEY()
	{
		return SMSKEY;
	}

	public static void setSMSKEY(String sMSKEY)
	{
		SMSKEY = sMSKEY;
	}

	public static String getSMSSECRET()
	{
		return SMSSECRET;
	}

	public static void setSMSSECRET(String sMSSECRET)
	{
		SMSSECRET = sMSSECRET;
	}

	public static String getSMSFREESIGNNAME()
	{
		return SMSFREESIGNNAME;
	}

	public static void setSMSFREESIGNNAME(String sMSFREESIGNNAME)
	{
		SMSFREESIGNNAME = sMSFREESIGNNAME;
	}

	public static String getSMSTEMPLATECODE()
	{
		return SMSTEMPLATECODE;
	}

	public static void setSMSTEMPLATECODE(String sMSTEMPLATECODE)
	{
		SMSTEMPLATECODE = sMSTEMPLATECODE;
	}

	public static String getLOGINCOUNT()
	{
		return LOGINCOUNT;
	}

	public static void setLOGINCOUNT(String mLOGINCOUNT)
	{
		LOGINCOUNT = mLOGINCOUNT;
	}

	public static String getMULTIQUERYURL()
	{
		return MULTIQUERYURL;
	}

	public static void setMULTIQUERYURL(String mULTIQUERYURL)
	{
		MULTIQUERYURL = mULTIQUERYURL;
	}


	/**
	 * @return the iNTERVALTIME
	 */
	public static String getINTERVALTIME()
	{
		return INTERVALTIME;
	}

	/**
	 * @param iNTERVALTIME
	 *            the iNTERVALTIME to set
	 */
	public static void setINTERVALTIME(String iNTERVALTIME)
	{
		INTERVALTIME = iNTERVALTIME;
	}

	public static String getMAINTOPIMG()
	{
		return MAINTOPIMG;
	}

	public static void setMAINTOPIMG(String mAINTOPIMG)
	{
		MAINTOPIMG = mAINTOPIMG;
	}

	/**
	 * @return the sMSACTION
	 */
	public static String getSMSACTION()
	{
		return SMSACTION;
	}

	/**
	 * @param sMSACTION
	 *            the sMSACTION to set
	 */
	public static void setSMSACTION(String sMSACTION)
	{
		SMSACTION = sMSACTION;
	}

	/**
	 * @return the sMSUSERID
	 */
	public static String getSMSUSERID()
	{
		return SMSUSERID;
	}

	/**
	 * @param sMSUSERID
	 *            the sMSUSERID to set
	 */
	public static void setSMSUSERID(String sMSUSERID)
	{
		SMSUSERID = sMSUSERID;
	}

	/**
	 * @return the sMSACCOUNT
	 */
	public static String getSMSACCOUNT()
	{
		return SMSACCOUNT;
	}

	/**
	 * @param sMSACCOUNT
	 *            the sMSACCOUNT to set
	 */
	public static void setSMSACCOUNT(String sMSACCOUNT)
	{
		SMSACCOUNT = sMSACCOUNT;
	}

	/**
	 * @return the sMSPASSWORD
	 */
	public static String getSMSPASSWORD()
	{
		return SMSPASSWORD;
	}

	/**
	 * @param sMSPASSWORD
	 *            the sMSPASSWORD to set
	 */
	public static void setSMSPASSWORD(String sMSPASSWORD)
	{
		SMSPASSWORD = sMSPASSWORD;
	}

	/**
	 * @return the sMSURL
	 */
	public static String getSMSURL()
	{
		return SMSURL;
	}

	/**
	 * @param sMSURL
	 *            the sMSURL to set
	 */
	public static void setSMSURL(String sMSURL)
	{
		SMSURL = sMSURL;
	}

	/**
	 * @return the sMSCOUNT
	 */
	public static String getSMSCOUNT()
	{
		return SMSCOUNT;
	}

	/**
	 * @param sMSCOUNT
	 *            the sMSCOUNT to set
	 */
	public static void setSMSCOUNT(String sMSCOUNT)
	{
		SMSCOUNT = sMSCOUNT;
	}

	/**
	 * @return the sMSSWITCH
	 */
	public static String getSMSSWITCH()
	{
		return SMSSWITCH;
	}

	/**
	 * @param sMSSWITCH
	 *            the sMSSWITCH to set
	 */
	public static void setSMSSWITCH(String sMSSWITCH)
	{
		SMSSWITCH = sMSSWITCH;
	}

	public static String SYS_DFC_HESGX = "";
	public static String SYS_DFC_WEBURL = "";
	public static String SYS_DFC_HOMEURL = "";
	public static String SYS_LOG_LOGGRADE = "";
	public static String SYS_LOG_DATABASELOGTYPE = "";

	public static String SYS_SYSTITLE = "";
	public static String SYS_SYSTITLEEN = "";
	public static String SYS_SYSBOTTOM = "";

	/**
	 * @return the sMSCONTENT
	 */
	public static String getSMSCONTENT()
	{
		return SMSCONTENT;
	}

	public static List<Map> SYS_MODULES;
	public static String SYS_TEMPLATEPATH = "";
	public static String SYS_MAINBGIMG = "";
	public static String SYS_MAINJGIMG = "";
	/************************************************/
	public static final String SESSION_USER = "sessionUser";

	public final static String CODE_PROVINCECODE = "provinceCode";
	public final static String CODE_PROVINCECODENAME = "provinceCodeName";

	public final static String MAPPOINTX = "mapPointX";
	public final static String MAPPOINTY = "mapPointY";

	public final static String RESP_MESSAGE_TYPE_TEXT = null;
	public final static String REQ_MESSAGE_TYPE_TEXT = null;
	public final static String OPERATE_TYPE_ADD = "0";
	public final static String OPERATE_TYPE_UPDATE = "1";
	public final static String OPERATE_TYPE_DELETE = "2";

	public final static String RESULT_FAILED = "0";
	public final static String RESULT_SUCCESS = "1";

	public final static String ORDER_STAT_OPEN = "O";
	public final static String ORDER_STAT_CLOSE = "S";
	public final static String USER_INFO_SESSION = "userSessionInfo";
	public final static String USER_SYS_CODE = "sysCode";
	public final static String USER_SYS_RELATION_CODE = "userSysRelationCode";
	public final static String WEB_URL = "weburl";
	public final static String KEY = "FZYD+JJ";
	public final static String USER_TYPE = "U";
	public final static String WOMEN_TYPE = "W";
	public final static String DEPT_TYPE = "D";

	public final static String LOG_TYPE_USER = "UL";
	public final static String LOG_TYPE_DATA_UPLAD = "DATA_UP";
	public final static String LOG_TYPE_SYS = "SL";
	public final static String LOG_TYPE_ROLE = "RL";
	public final static String LOG_TYPE_ERROR = "EL";
	public final static String LOG_TYPE_PANVIEW_ENT = "PVE";
	public final static String LOG_TYPE_CLAIM_ENT = "CLAIMENT";
	public final static String WEIXIN_ACCESS_TOKEN = "access_token";
	/************************ 批量上传 ************************/
	public final static String TEMPLATETYPE = "template_type";
	public final static String TEMPLATEDATA = "template_data";
	public final static String BATCHIMPORTMAPPER = "BatchImportMapper";
	/**
	 * 地图参数变量
	 */
	public final static String NUM = "0";
	public final static String NUMBEFORE = "1";
	public final static String NUMAFTER = "2";

	/*public static String encodePassword(String pwd)
	{
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		md5.setEncodeHashAsBase64(true);
		return md5.encodePassword(KEY, pwd);
	}*/

	public final static String encodePasswordMD5(String username, String password)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try
		{
			byte[] btInput = password.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest(username.getBytes());
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args)
	{
		System.out.print(Constant.encodePasswordMD5("admin", "jxzr123456"));
	}

	public static String getSYS_DFC_PROVINCESHORTCODE()
	{
		return SYS_DFC_PROVINCESHORTCODE;
	}

	public static void setSYS_DFC_PROVINCESHORTCODE(String sYS_DFC_PROVINCESHORTCODE)
	{
		SYS_DFC_PROVINCESHORTCODE = sYS_DFC_PROVINCESHORTCODE;
	}

	public static String getSYS_DFC_PROVINCECODE()
	{
		return SYS_DFC_PROVINCECODE;
	}

	public static void setSYS_DFC_PROVINCECODE(String sYS_DFC_PROVINCECODE)
	{
		SYS_DFC_PROVINCECODE = sYS_DFC_PROVINCECODE;
	}

	public static String getSYS_DFC_PROVINCECODENAME()
	{
		return SYS_DFC_PROVINCECODENAME;
	}

	public static void setSYS_DFC_PROVINCECODENAME(String sYS_DFC_PROVINCECODENAME)
	{
		SYS_DFC_PROVINCECODENAME = sYS_DFC_PROVINCECODENAME;
	}

	public static String getSYS_DFC_MAPPOINTX()
	{
		return SYS_DFC_MAPPOINTX;
	}

	public static void setSYS_DFC_MAPPOINTX(String sYS_DFC_MAPPOINTX)
	{
		SYS_DFC_MAPPOINTX = sYS_DFC_MAPPOINTX;
	}

	public static String getSYS_DFC_MAPPOINTY()
	{
		return SYS_DFC_MAPPOINTY;
	}

	public static void setSYS_DFC_MAPPOINTY(String sYS_DFC_MAPPOINTY)
	{
		SYS_DFC_MAPPOINTY = sYS_DFC_MAPPOINTY;
	}

	public static String getSYS_DFC_HESGX()
	{
		return SYS_DFC_HESGX;
	}

	public static void setSYS_DFC_HESGX(String sYS_DFC_HESGX)
	{
		SYS_DFC_HESGX = sYS_DFC_HESGX;
	}

	public static String getSYS_DFC_WEBURL()
	{
		return SYS_DFC_WEBURL;
	}

	public static void setSYS_DFC_WEBURL(String sYS_DFC_WEBURL)
	{
		SYS_DFC_WEBURL = sYS_DFC_WEBURL;
	}

	public static void setSYS_DFC_HOMEURL(String sYS_DFC_HOMEURL)
	{
		SYS_DFC_HOMEURL = sYS_DFC_HOMEURL;
	}

	public static String getSYS_LOG_LOGGRADE()
	{
		return SYS_LOG_LOGGRADE;
	}

	public static void setSYS_LOG_LOGGRADE(String sYS_LOG_LOGGRADE)
	{
		SYS_LOG_LOGGRADE = sYS_LOG_LOGGRADE;
	}

	public static String getSYS_LOG_DATABASELOGTYPE()
	{
		return SYS_LOG_DATABASELOGTYPE;
	}

	public static void setSYS_LOG_DATABASELOGTYPE(String sYS_LOG_DATABASELOGTYPE)
	{
		SYS_LOG_DATABASELOGTYPE = sYS_LOG_DATABASELOGTYPE;
	}

	public static String getSYS_SYSTITLE()
	{
		return SYS_SYSTITLE;
	}

	public static void setSYS_SYSTITLE(String sYS_SYSTITLE)
	{
		SYS_SYSTITLE = sYS_SYSTITLE;
	}
	public static String getSYS_SYSTITLEEN()
	{
		return SYS_SYSTITLEEN;
	}

	public static void setSYS_SYSTITLEEN(String sYS_SYSTITLE)
	{
		SYS_SYSTITLEEN = sYS_SYSTITLE;
	}
	public static String getSYS_SYSBOTTOM()
	{
		return SYS_SYSBOTTOM;
	}

	public static void setSYS_SYSBOTTOM(String sYS_SYSBOTTOM)
	{
		SYS_SYSBOTTOM = sYS_SYSBOTTOM;
	}

	public static List<Map> getSYS_MODULES()
	{
		return SYS_MODULES;
	}

	public static void setSYS_MODULES(List sYS_MODULES)
	{
		SYS_MODULES = sYS_MODULES;
	}

	public static String getSYS_TEMPLATEPATH()
	{
		return SYS_TEMPLATEPATH;
	}

	public static void setSYS_TEMPLATEPATH(String sYS_TEMPLATEPATH)
	{
		SYS_TEMPLATEPATH = sYS_TEMPLATEPATH;
	}

	public static String getSYS_MAINBGIMG()
	{
		return SYS_MAINBGIMG;
	}

	public static void setSYS_MAINBGIMG(String sYS_MAINBGIMG)
	{
		SYS_MAINBGIMG = sYS_MAINBGIMG;
	}

	public static String getSYS_MAINJGIMG()
	{
		return SYS_MAINJGIMG;
	}

	public static void setSYS_MAINJGIMG(String sYS_MAINJGIMG)
	{
		SYS_MAINJGIMG = sYS_MAINJGIMG;
	}

	public static void setSMSCONTENT(String smsContent)
	{
		SMSCONTENT = smsContent;
	}

	public static String getBACK_URL() {
		return BACK_URL;
	}

	public static void setBACK_URL(String bACK_URL) {
		BACK_URL = bACK_URL;
	}

	public static String getVIDEO_NAME() {
		return VIDEO_NAME;
	}

	public static void setVIDEO_NAME(String vIDEO_NAME) {
		VIDEO_NAME = vIDEO_NAME;
	}

	public static String getVIDEO_PWD() {
		return VIDEO_PWD;
	}

	public static void setVIDEO_PWD(String vIDEO_PWD) {
		VIDEO_PWD = vIDEO_PWD;
	}

	public static String getVIDEO_IP() {
		return VIDEO_IP;
	}

	public static void setVIDEO_IP(String vIDEO_IP) {
		VIDEO_IP = vIDEO_IP;
	}

	public static String getVIDEO_PORT() {
		return VIDEO_PORT;
	}

	public static void setVIDEO_PORT(String vIDEO_PORT) {
		VIDEO_PORT = vIDEO_PORT;
	}

	public static String getVIDEO_NUM() {
		return VIDEO_NUM;
	}

	public static void setVIDEO_NUM(String vIDEO_NUM) {
		VIDEO_NUM = vIDEO_NUM;
	}


}
