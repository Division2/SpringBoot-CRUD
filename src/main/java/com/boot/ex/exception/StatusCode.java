package com.boot.ex.exception;

public class StatusCode {

	public static final int OK = 200;						//요청 성공에 따른 응답 코드
	public static final int CREATED = 201;					//요청 성공에 따른 새로운 리소스 생성 응답 코드(POST, PUT)
	public static final int BAD_REQUEST = 400;				//잘못된 요청에 따른 응답 코드
	public static final int UNAUTHORIZED = 401;				//인증되지 않은 사용자의 요청에 따른 응답 코드
	public static final int FORBIDDEN = 403;				//접근 권한이 없는 사용자의 요청에 따른 응답 코드
	public static final int NOT_FOUND = 404;				//리소스가 없는 요청에 따른 응답 코드
	public static final int METHOD_NOT_ALLOWED = 405;		//지원하지 않는 HTTP Method로 요청이 들어온 경우의 응답 코드
	public static final int CONFLICT = 409;					//대상 리소스의 현재 상태와 충돌하여 요청을 완료할 수 없을 경우의 응답 코드
	public static final int INTERNAL_SERVER_ERROR = 500;	//서버에 문제가 있을 경우의 응답 코드
}