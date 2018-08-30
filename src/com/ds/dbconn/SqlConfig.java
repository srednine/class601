package com.ds.dbconn;

public class SqlConfig {
	
	//public final static String
		
	/**
	 * 로그인 시에 쓰임.
	 */
	public final static String login = "  SELECT MEM_ID, MEM_NAME \n    FROM RN_MEMBER \n   WHERE MEM_ID = ? AND MEM_PW = ?";

	/**
	 * 아이디가 있는지 없는지
	 */
	public final static String idchk = "SELECT COUNT(1) as CNT FROM RN_MEMBER WHERE MEM_ID = ?";

	/**
	 * 로그인 시에 입력한 정보가 있는지
	 */
	public final static String loginchk = "SELECT COUNT(1) as CNT FROM RN_MEMBER \n WHERE MEM_ID = ? AND MEM_PW = ?";

	/**
	 * member 회원가입
	 */
	public final static String memberInsert =
			    "INSERT INTO RN_MEMBER \n"
			  + "      (               \n"
			  + "        MEM_ID        \n"
			  + "      , MEM_PW        \n"
			  + "      , MEM_NAME      \n"
			  + "      , MEM_EMAIL     \n"
			  + "      , MEM_AGE       \n"
			  + "      , MEM_ADDR1     \n"
			  + "      , MEM_ADDR2     \n"
			  + "      , MEM_REGDT     \n"
			  + "      )               \n"
			  + "        VALUES        \n"
			  + "      (               \n"
			  + "        ?             \n"
			  + "      , ?             \n"
			  + "      , ?             \n"
			  + "      , ?             \n"
			  + "      , ?             \n"
			  + "      , ?             \n"
			  + "      , ?             \n"
			  + "      , SYSDATE       \n"
			  + "      )               \n";
	
	/**
	 * 회원정보 수정 시 회원정보 가져오기
	 */
	public final static String memberSelectOne =   
			    "  SELECT MEM_ID     \n"
			  + "       , MEM_PW     \n"
			  + "       , MEM_NAME   \n"
			  + "       , MEM_EMAIL  \n"
			  + "       , MEM_AGE    \n"
			  + "       , MEM_ADDR1  \n"
			  + "       , MEM_ADDR2  \n"
			  + "    FROM RN_MEMBER  \n"
			  + "   WHERE MEM_ID = ? \n";
	
	/**
	 * 회원정보값 수정
	 */
	public final static String memberEdit = 
			  "  UPDATE RN_MEMBER     \n"
			 +"     SET MEM_PW    = ? \n"
			 +"       , MEM_NAME  = ? \n"
			 +"       , MEM_EMAIL = ? \n"
			 +"       , MEM_AGE   = ? \n"
			 +"       , MEM_ADDR1 = ? \n"
			 +"       , MEM_ADDR2 = ? \n"
			 +"   WHERE MEM_ID    = ? \n";

	/**
	 * 게시물 전체 row 갯수
	 */
	public final static String boardListCount = "SELECT COUNT(1) as CNT FROM RN_BOARD";
	
	/**
	 * 게시물 전체 출력
	 */
	public final static String boardList = 
 			  "  SELECT * \n"
			 +"    FROM ( \n"
			 +"            SELECT BRD_NO \n"
			 +"                 , BRD_TITLE \n"
			 +"                 , BRD_WRITER \n"
			 +"                 , BRD_CONTENT \n"
			 +"                 , BRD_DATE \n"
			 +"                 , BRD_HIT \n"
			 +"                 , BRD_SECRET \n"
			 +"                 , ROW_NUMBER() OVER (ORDER BY BRD_DATE DESC, BRD_NO DESC) as RNUM \n"
			 +"              FROM RN_BOARD \n"
			 +"         ) T \n"
			 +"   WHERE RNUM BETWEEN ? AND ? "
			 //+"ORDER BY RNUM DESC"
			 ;
	
	
	
	
	
	
	
	
	
}
