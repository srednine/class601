package com.ds.util;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.ds.dbconn.OraConn;

/**
 * <b>★사용법★</b><br/>
 * 1. 클래스 생성 시 필요한 매개변수
 * <pre>
 * 첫번째 : 리턴하고자 하는 객체(생략 시 return은 HashMap)
 *        주의사항! 꼭 초기화 된 객체로 사용!
 *        예시 1) new Integer(0)
 *        예시 2) new String()
 *        예시 3) new RN_Member()
 *        
 * 두번째 : DB 에 접속할 Connection 정보(☆필수)
 *        예시 1) OraConn.getConn()
 *        
 * 세번째 : SQL문 (☆필수, sql문만 모아놓은 클래스 만들어놓는게 편함.)
 *        주의사항! 쿼리문에 ?가 있을 경우 네번째 매개변수 필수!
 *               쿼리문 출력 시 줄바꿈 하고싶을 경우엔 원하는 위치에 \n 삽입! 
 *        예시 1) SELECT 1 as TMP \n  FROM DUAL
 *        예시 2) DELETE FROM RN_MEMBER WHERE MEM_ID ?
 *        
 * 네번째 : 쿼리문에 set해야 할 값(세번째 매개변수에 ?가 있으면 생략불가)
 *        주의사항! 기존 서블릿 사용하는거와 마찬가지로 ? 에 넣을 값들을
 *               순서에 맞춰서 넣어주세요!
 *        예시 1) Object[] objs = new Object[넣을 값의 갯수];
 *              objs[0] = (int) 1; objs[1] = "1234";
 *              선언시에 objs를 넣으면 됨.
 *        예시 2) Object...setValues 이렇게 선언 되어있으므로
 *              객체생성 시 (int) 1, "1234" 식으로도 가능함.
 * </pre>
 * 2. 사용가능한 메소드
 * <pre>
 * SELECT 용도
 *   >> selectOne() : SELECT된 행의 갯수가 1일 경우
 *        예시 1) int temp = new DBHelper(new Integer(0), 그 외 매개변수들..).selectOne();
 *        예시 2) RN_Member vo = new DBHelper(new RN_Member(), 그 외 매개변수들..).selectOne();
 *        예시 3) Map<String, Object> map = new DBHelper(매개변수들..).selectOne();
 *   >> selectList() : SELECT된 행의 갯수가 2 이상일 경우
 *        예시 1) List<Integer> temp = new DBHelper(new Integer(0), 그 외 매개변수들..).selectList();
 *        예시 2) List<RN_Member> vo = new DBHelper(new RN_Member(), 그 외 매개변수들..).selectList();
 *        예시 3) List<Map<String, Object>> map = new DBHelper(매개변수들..).selectList();
 *        
 * INSERT/UPDATE/DELETE 용도
 *   >> insUpDel()
 *        예시 1) new DBHelper(매개변수들..).insUpDel();
 * </pre>
 * 3. 로그출력기능 해제/미해제
 * <pre>
 * 작성한 쿼리문 출력 해제/미해제 (기본값은 출력) : changeQueryLog() 
 *        예시 1) DBHelper dbHelper = new DBHelper(매개변수들..);
 *              dbHelper.changeQueryLog(); >> 쿼리문 출력기능 해제
 *              
 * 조회된 SELECT 로그 출력 해제/미해제 (기본값은 출력) : changeResultSetLog() 
 *        예시 1) DBHelper dbHelper = new DBHelper(매개변수들..);
 *              dbHelper.changeResultSetLog(); >> SELECT 조회물 출력기능 해제
 *              
 * 작성한 쿼리문, 조회된 SELECT 로그 출력 동시에 해제/미해제 : changeQueryResultSetLog() 
 *        예시 1) DBHelper dbHelper = new DBHelper(매개변수들..);
 *              dbHelper.changeQueryResultSetLog(); >> 쿼리문, SELECT 조회물 출력기능 해제
 *       
 * </pre>
 * @author RedNine
 * 18.08.10 ~ 18.08.13 
 * @version 1.0
 */
public class DBHelper implements Cloneable {

    /**
     * 사용 예시
    //String sql = "SELECT COUNT(1) as CNT FROM RN_BOARD WHERE 1 = ? AND 1 = ? AND 1 = ? AND '나니고래' = ? ";
    //int plz = new DBHelper(new Integer(0), OraConn.getConn(), sql, (int)1, (int)1, (int)1 , "나니고래").selectOne();
    
    //String sql = "SELECT '1' as CNT FROM RN_BOARD WHERE 1 = ? AND 1 = ? AND 1 = ?"
    //String plz = new DBHelper(new String(), OraConn.getConn(), sql, (int)1, (int)1, (int)1).selectOne();
    
    //String sql = "SELECT * FROM RN_MEMBER WHERE 1 = ? "
    //RN_Member plz = new DBHelper(new RN_Member(), OraConn.getConn(), sql, (int)1).selectOne();
        
    //String sql = "SELECT * FROM RN_BOARD"
    //Map<String, Object> plz = new DBHelper(OraConn.getConn(), sql).selectOne();
    
    //List<RN_Member> plz = new DBHelper(new RN_Member(), OraConn.getConn(), sql).selectList();
    //List<RN_Member> plz = new DBHelper(new RN_Member(), OraConn.getConn(), sql).selectList();
        
    //디버깅
    //System.out.println("plz : " + plz);
    //System.out.println("plz.getClass() : " + plz.getClass().getName());
     */

	/**
	 * return을 위해 잠시 담아두는 값
	 */
	private Object temporaryValue;

	/**
	 * return할 클래스
	 */
	private Object returnClass;

	//생성자를 통해 값이 할당됨.
	private PreparedStatement ps;
	private ResultSet rs;
	private ResultSetMetaData rsmd;

	/**
	 * 쿼리문 로고 찍을 때 info 날짜 값
	 */
	private String logSqlInfo = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime())) + "  INFO ";

	/**
	 * 현재 row 
	 */
	private int getRow = 0;

	/**
	 * 전체 row 갯수 
	 */
	private int rowCount;

	/**
	 * 칼럼갯수 
	 */
	private int columnCount;   

	/**
	 * 각 칼럼의 최대길이
	 */
	private int[] columnMAXLength;

	/**
	 * 칼럼이름
	 */
	private String[] columnName;

	/**
	 * 칼럼이름길이
	 */
	private int[] columnNameLength;

	/**
	 * 실제쿼리 결과값
	 */
	private String[][] logData;

	/**
	 * 결과값 길이
	 */
	private int[][] logDataLength;   

	/**
	 * returnClass의 타입을 알려줌
	 */
	private String[] judgmentClass;

	/**
	 * 작성한 쿼리 로그 출력여부
	 */
	private boolean SystemOutPrintQueryLogYN = true;

	/**
	 * 조회된 SELECT 로그 출력여부
	 */
	private boolean SystemOutPrintResultSetLogYN = true;
	
	/**
	 * one 인지 list인지 판단을 위함.
	 */
	private String selectOneList;
	
	public DBHelper(Connection con, String sql) {
		this.ps = makePreparedStatement(con, sql + " ");
		this.returnClass = new HashMap<String, Object>();
	}   

	public <T> DBHelper(T returnClass, Connection con, String sql) {
		this.ps = makePreparedStatement(con, sql + " ");
		this.returnClass = returnClass;
	}

	public DBHelper(Connection con, String sql, Object...setValues) {
		this.ps = makePreparedStatement(con, sql + " ", setValues);
		this.returnClass = new HashMap<String, Object>();
	}

	public <T> DBHelper(T returnClass, Connection con, String sql, Object...setValues) {
		this.ps = makePreparedStatement(con, sql + " ", setValues);
		this.returnClass = returnClass;
	}

	/**
	 * select갯수가 1개일 경우에 사용 return 넣을 클래스
	 */
	@SuppressWarnings("unchecked")
	public <T> T selectOne() {
		
		selectOneList = "one";
		
		try {
			
			//처리 할 데이터들을 먼저 셋팅한다.
			setDataAndLength();

			//리턴할 객체 생성
			T returnValue = null;

			//return할 값을 만들어준다.
			while (rs.next()) {
				returnValue = (T) getValueOne();
				getRow ++;
			}

			//디버깅
			//showMyValues();

			if (SystemOutPrintResultSetLogYN) {
				//로그찍어주기
				SystemOutPrintResultsettable();         
			}      

			return returnValue;
			
		} catch (Exception e) {
			return (T) RNCommon.exceptionRtn(e, "selectOne ERROR!! [t/c]", "o");
		}
	}

	/**
	 * select갯수가 n개일 경우에 사용
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> selectList() {
		
		selectOneList = "list";
		
		try {

			//처리 할 데이터들을 먼저 셋팅한다.
			setDataAndLength();

			//리턴할 객체 생성
			List<T> returnValueList = new ArrayList<T>();

			//return할 값을 만들어준다.
			while (rs.next()) { 
				
				returnValueList.add((T) getValueOne());
				getRow ++;
				
			}
			System.out.println("returnValueList : " + returnValueList);

			//디버깅
			//showMyValues();

			if (SystemOutPrintResultSetLogYN) {
				//로그찍어주기
				SystemOutPrintResultsettable();         
			}

			return returnValueList;
			
		} catch (Exception e) {
			return (List<T>) RNCommon.exceptionRtn(e, "selectList ERROR!! [t/c]", "o");
		}
	}

	/**
	 * 작성한 쿼리 로그 상태값 변경
	 */
	public void changeQueryLog() {
		SystemOutPrintQueryLogYN = !SystemOutPrintQueryLogYN;
	}

	/**
	 * 조회된 SELECT 로그 상태값 변경
	 */
	public void changeResultSetLog() {
		SystemOutPrintResultSetLogYN = !SystemOutPrintResultSetLogYN;
	}

	/**
	 * 작성한 쿼리 로고 상태값, 조회된 SELECT 로그 상태값 변경
	 */
	public void changeQueryResultSetLog() {
		SystemOutPrintQueryLogYN = !SystemOutPrintQueryLogYN;
		SystemOutPrintResultSetLogYN = !SystemOutPrintResultSetLogYN;
	}


	/**************************************************************************************************************/
	/********************************            Insert/Update/Delete                ******************************/
	/**************************************************************************************************************/

	/**
	 * Insert, Update, Delete 통합으로 처리
	 * @param con
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int insUpDel() {   
		try {
			return ps.executeUpdate();
		} catch (SQLException e) {
			return (int) RNCommon.exceptionRtn(e, "insUpDel ERROR!! [t/c]", "i");
		}   
	}


	/**************************************************************************************************************/
	/********************************                  필요한 기능들                                ******************************/
	/**************************************************************************************************************/
		
	/**
	 * select 된 값을 returnValueOne에 담아 리턴함.
	 */
	@SuppressWarnings("unchecked")
	private Object getValueOne() {
		
		try {
			
			//return 할 값
			Object returnObjValue;

			if (selectOneList.equals("one")) {
				returnObjValue = returnClass;
			} else {
				returnObjValue = returnClass.getClass().newInstance();				
			}


			for (int i = 0; i < columnCount; i++) {

				temporaryValue = whatIsUrType(i + 1);
				logData[getRow][i] = String.valueOf(temporaryValue);

				logDataLength[getRow][i] = logData[getRow][i].length();
				int longLength = 0;

				//row 가 1번째일 경우엔 칼럼이름의 길이를 반드시 비교해야함.
				if (getRow == 0) {
					columnName[i] = rsmd.getColumnName(i+1);
					columnNameLength[i] = columnName[i].length();
					if (logDataLength[getRow][i] > columnNameLength[i]) {
						longLength = logDataLength[getRow][i]; 
					} else {
						longLength = columnNameLength[i];
					}

					//그 이외엔 데이터 길이만 비교하면 됨.
				} else {            
					if (columnMAXLength[i] > logDataLength[getRow][i]) {
						longLength = columnMAXLength[i]; 
					} else {
						longLength = logDataLength[getRow][i];
					}
				}

				columnMAXLength[i] = longLength;

				//int, String 등으로 넘어오면 바로 처리
				if ((judgmentClass[0] + "." + judgmentClass[1]).equals("java.lang")) {

					returnObjValue = temporaryValue;

					//Map일 경우
				} else if ((judgmentClass[0] + "." + judgmentClass[1] + "." + judgmentClass[2]).equals("java.util.HashMap")) {

					((HashMap<String, Object>) returnObjValue).put(columnName[i].toUpperCase(), temporaryValue);

					//사용자가 만든 class일 경우
				} else {

					//setter 찾기
					String setMethodString = "set";
					String methodString = null;

					//setMem_id 와 같은 setter 함수를 만듬.
					methodString = setMethodString+columnName[i].substring(0,1).toUpperCase()+columnName[i].substring(1).toLowerCase();
					//System.out.println("methodString : " + methodString);

					//return 시킬 객체의 모든 메소드를 가져옴
					Method[] methods = returnClass.getClass().getDeclaredMethods();
					//System.out.println("methods : " + Arrays.toString(methods));

					//setter 메소드가 있는지 찾는다
					int methodsCount = methods.length;
					for (int j = 0; j < methodsCount; j++) {
						if(methodString.equals(methods[j].getName())){
							methods[j].invoke(returnObjValue, temporaryValue);
						}
					}

				}//if

			}//for  

			return returnObjValue;
			
		} catch (Exception e) {
			return RNCommon.exceptionRtn(e, "getValueOne ERROR!! [t/c]", "o");
		}
	}

	/**
	 * 저장된 변수들의 값을 출력함. (디버깅용도)
	 */
	private void showMyValues() {
		System.out.println("returnValueOne : " + returnClass);
		System.out.println("returnValueOne.getClass() : " + returnClass.getClass().getName());
		System.out.println("getRow : " + getRow);
		System.out.println("rowCount : " + rowCount);
		System.out.println("columnCount : " + columnCount);
		System.out.println("columnMAXLength : " + Arrays.toString(columnMAXLength));
		System.out.println("columnName : " + Arrays.toString(columnName));
		System.out.println("columnNameLength : " + Arrays.toString(columnNameLength));
		System.out.println("logData : " + Arrays.deepToString(logData));
		System.out.println("logDataLength : " + Arrays.deepToString(logDataLength));
	}

	/**
	 * ResultSet 데이터들을 저장 & 각 변수별 길이 저장
	 * @throws Exception
	 */
	private void setDataAndLength() {
		
		try {
			
			//쿼리문 실행
			this.rs = ps.executeQuery();
			this.rsmd = rs.getMetaData();
			
			rs.last();
			rowCount = rs.getRow();
			rs.beforeFirst();

			columnCount = rsmd.getColumnCount();
			columnMAXLength = new int[columnCount];
			columnName = new String[columnCount];
			columnNameLength = new int[columnCount];

			logData = new String[rowCount][columnCount];
			logDataLength = new int[rowCount][columnCount];

			judgmentClass = returnClass.getClass().getName().split("\\.");

			//디버깅
			//System.out.println("returnClass : " + returnValueOne.getClass().getName());
			//System.out.println("judgmentClass : " + Arrays.toString(judgmentClass));
			
		} catch (Exception e) {
			RNCommon.exceptionRtn(e, "setDataAndLength ERROR!! [t/c]");
		}
	}

	/**
	 * index 번 째의 rsmd의 데이터타입대로 return 시켜줌.
	 * @param rsmd
	 * @param index
	 * @return
	 * @throws Exception 
	 */
	private Object whatIsUrType(int index) {
		
		try {
			
			switch (rsmd.getColumnType(index)) {
			case Types.NUMERIC:
			case Types.INTEGER:
				return rs.getInt(index);
			case Types.FLOAT:
				return rs.getFloat(index);
			case Types.DOUBLE:
				return rs.getDouble(index);
			case Types.DATE:
			case Types.TIMESTAMP:
				return rs.getDate(index);
			default:
				return rs.getString(index);
			}
			
		} catch (Exception e) {
			return RNCommon.exceptionRtn(e, "whatIsUrType ERROR!! [t/c]", "o");
		}
	}

	/**
	 * select 결과를 console 창에 뿌려준다.
	 * i : row, j : 칼럼 수대로, k : 데이터 길이대로
	 * @param rowCount : 전체 row 갯수
	 * @param ColumnCount : 칼럼 갯수
	 * @param ColumnMAXLength : 칼럼의 최대 길이
	 * @param columnNameLength : 칼럼이름 길이
	 * @param columnName : 칼럼이름
	 * @param logDataLength : 데이터길이
	 * @param logData : 데이터
	 */
	private void SystemOutPrintResultsettable() {

		System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime())) + "  INFO : jdbc.resultsettable - ");

		//뚜껑부터
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < columnCount; j++) {

				System.out.print("|");

				String kOutText = "-";   //출력할 문자열
				int kCount = columnMAXLength[j] + 1;   //몇번 돌릴지

				//칼럼이름만 찍어줄 경우
				if (i == 1) {
					kOutText = " ";
					kCount -= columnNameLength[j];
					System.out.print(columnName[j]);
				}

				//값을 찍어줌.
				for (int k = 0; k < kCount; k++) System.out.print(kOutText);
			}
			System.out.println("|");
		}

		//실제 데이터 & 젤 밑
		for (int i = 0; i <= rowCount; i++) {         
			for (int j = 0; j < columnCount; j++) {      

				System.out.print("|");

				String kOutText = "-";   //출력할 문자열
				int kCount = columnMAXLength[j] + 1;   //몇번 돌릴지

				//마지막 행일 경우
				if (i != rowCount) {
					kOutText = " ";
					kCount -=logDataLength[i][j];
					System.out.print(logData[i][j]);               
				}

				for (int k = 0; k < kCount; k++) System.out.print(kOutText);      
			}            
			System.out.println("|");               
		}      
		System.out.println("∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽");
	}



	/**************************************************************************************************************/
	/********************************      PreparedStatement 만들고 Query Log 찍기       ******************************/
	/**************************************************************************************************************/

	/**
	 * 쿼리문이 날라오면 완전한 PreparedStatement로 만들어주는 메소드.<p>
	 * 쿼리 로그만 찍어주면 된다.<p>
	 * @author RedNine
	 * @param con DB접속정보 Connection
	 * @param getSql 쿼리문
	 * @param values PreparedStatement에 set할 데이터들
	 * @return
	 * @throws SQLException
	 */
	private PreparedStatement makePreparedStatement(Connection con, String getSql) {

		try {
			
			if (SystemOutPrintQueryLogYN) {
				//set할 값과 완성된 쿼리문 출력해준다.
				System.out.println("∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽");
				//System.out.println(logSqlInfo + "SQL : \n" + getSql);
				System.out.println(logSqlInfo + "SQL >> ");
				System.out.println(getSql);
				System.out.println("∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽");         
			}      

			//set까지 완료된 완성된 PreparedStatement
			return con.prepareStatement(getSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
		} catch (Exception e) {
			return (PreparedStatement) RNCommon.exceptionRtn(e, "makePreparedStatement ERROR!! [t/c]", "o");
		}
	}

	/**
	 * '?'이 있는 쿼리문이 날라오면 완전한 PreparedStatement로 만들어주는 메소드<p>
	 * Object... 와 Object[]의 차이는 없음.
	 * @author RedNine
	 * @param con DB접속정보 Connection
	 * @param getSql 쿼리문
	 * @param values PreparedStatement에 set할 데이터들
	 * @return
	 * @throws SQLException
	 */
	private PreparedStatement makePreparedStatement(Connection con, String getSql, Object... setValues) {
		
		try {
			
			//?으로 나누고나서 일단 담을 배열.
			String[] logSqlArr = getSql.split("\\?");

			//for문 돌리기 위해 배열길이 구해놓음.
			int logSqlArrLen = setValues.length;

			//log찍을 문자열.
			//excuteSqlArr을 for문 돌려서 set 시킬 데이터를 붙인 다음 한 문자열로 합쳐야하기 때문. 
			String logSql = logSqlArr[0];

			//쿼리 로그는 만들면서 직접 set 작업.
			PreparedStatement ps = con.prepareStatement(getSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			//작업 시작
			for (int i = 0; i < logSqlArrLen; i++) {

				//문자열로 다뤄야할 경우 setValues를 '로 감싸준다.
				if (
						(setValues[i] instanceof String)
						||   (setValues[i] instanceof Timestamp)
						||   (setValues[i] instanceof Boolean)
						||   (setValues[i] instanceof Date)
						) {            
					logSql += "'" + setValues[i] + "'" + logSqlArr[i+1];         
					/*
		         //문자열이 아닐경우 그냥 합친다.
		          } else if (
		               (setValues[i] instanceof Byte)
		             ||   (setValues[i] instanceof Integer)
		              ||   (setValues[i] instanceof Long)
		              ||   (setValues[i] instanceof Float)
		          ) {            
		            logSql += setValues[i] + logSqlArr[i+1];
					 */
					//위에서 못 걸렀을 경우에 
				} else {            
					logSql += setValues[i] + logSqlArr[i+1];      
				}         
				ps.setObject(i + 1, setValues[i]);
			}

			if (SystemOutPrintQueryLogYN) {
				//set할 값과 완성된 쿼리문 출력해준다.
				System.out.println("∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽");
				System.out.println("setValues : " + Arrays.toString(setValues));
				System.out.println(logSqlInfo + "SQL >> ");
				System.out.println(logSql);
				System.out.println("∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽ ∽");
			}

			//set까지 완료된 완성된 PreparedStatement
			return ps;
			
		} catch (Exception e) {
			return (PreparedStatement) RNCommon.exceptionRtn(e, "makePreparedStatement setValues ERROR!! [t/c]", "o");
		}
	}
}