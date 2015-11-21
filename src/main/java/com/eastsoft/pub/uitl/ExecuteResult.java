package com.eastsoft.pub.uitl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date:2013-1-9
 * @description:
 */
public class ExecuteResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * ���ؽ������
	 */
	private T result;
	/**
	 * �ɹ���ʾ��Ϣ
	 */
	private String successMessage;
	/**
	 * ��ͨ�Ĵ�����Ϣ
	 */
	private List<String> errorMessages = new ArrayList<String>();
	/**
	 * �ֶδ�����Ϣ-��key-value����ʽ����
	 */
	private Map<String, String> fieldErrors = new HashMap<String, String>();
	/**
	 * ������Ϣ
	 */
	private List<String> warningMessages = new ArrayList<String>();
	/**
	 * �쳣��Ϣ
	 */
	private Throwable throwable;

	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	/**
	 * �жϵ�ǰִ�н���Ƿ���ȷ�����errorMessages��fieldErrors��Ϊ�գ����޴���
	 * @return
	 */
	public boolean isSuccess(){
		return errorMessages.isEmpty() && fieldErrors.isEmpty();
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public List<String> getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}
	public void setFieldErrors(Map<String, String> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	public List<String> getWarningMessages() {
		return warningMessages;
	}
	public void setWarningMessages(List<String> warningMessages) {
		this.warningMessages = warningMessages;
	}
	/**
	 * ��Ӵ�����Ϣ���б���
	 * @param errorMessage
	 */
	public void addErrorMessage(String errorMessage){
		this.errorMessages.add(errorMessage);
	}
	/**
	 * ����ֶδ�����Ϣ���б���
	 * @param field �ֶ�����-key
	 * @param errorMessage ���ֶζ�Ӧ�Ĵ�����Ϣ-value
	 */
	public void addFieldError(String field,String errorMessage){
		this.fieldErrors.put(field, errorMessage);
	}
	/**
	 * ��Ӿ�����Ϣ���б���
	 * @param warningMessage
	 */
	public void addWarningMessage(String warningMessage){
		this.warningMessages.add(warningMessage);
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}