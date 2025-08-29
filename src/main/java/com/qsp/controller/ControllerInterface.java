package com.qsp.controller;

interface ControllerInterface {

	void saveStudent(int s_id, String s_name, long s_mobno, String s_email, String s_address);

	void updateStudent(int s_id);

	void deleteStudent(int s_id);

	void getDataById(int s_id);

	void getAllData();

}