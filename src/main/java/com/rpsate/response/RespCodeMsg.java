package com.rpsate.response;

import java.util.HashMap;
import java.util.Map;

public class RespCodeMsg {
    public static final Integer SUCCESS = 200001;
    public static final Integer FAIL = 200000;

    public static final Integer LOGIN_FAIL = 200010;
    public static final Integer LOGIN_SUCCESS = 200011;

    public static final Integer REGISTER_FAIL = 200020;
    public static final Integer REGISTER_SUCCESS = 200021;
    public static final Integer USER_EXIST = 20023;

    public static final Integer UPDATE_USER_FAIL = 200030;
    public static final Integer UPDATE_USER_SUCCESS = 20031;

    public static final Integer DELETE_USER_FAIL = 200040;
    public static final Integer DELETE_USER_SUCCESS = 20041;
    public static final Integer DELETE_USERS_FAIL = 20050;
    public static final Integer DELETE_USERS_SUCCESS = 20051;

    public static final Integer ADD_IMAGE_FAIL = 20060;
    public static final Integer ADD_IMAGE_SUCCESS = 20061;

    public static final Integer DELETE_IMAGE_FAIL = 20070;
    public static final Integer DELETE_IMAGE_SUCCESS = 20071;
    public static final Integer DELETE_IMAGES_FAIL = 20080;
    public static final Integer DELETE_IMAGES_SUCCESS = 20081;

    public static final Integer SELECT_IMAGE_FAIL = 20090;
    public static final Integer SELECT_IMAGE_SUCCESS = 20091;

    public static final Integer UPDATE_IMAGE_FAIL = 200100;
    public static final Integer UPDATE_IMAGE_SUCCESS = 200101;

    public static final Integer EXCEPTION = 50000;
    public static final Integer PARAMETER_ERR = 50001;
    public static final Integer URL_NOT_EXIST= 50002;
    public static final Integer ACCESS_DENIED = 50003;

    public static final Integer USERNAME_NOT_NULL = 50011;
    public static final Integer PASSWORD_NOT_NULL = 50012;
    public static final Integer ID_NOT_NULL = 50013;
    public static final Integer GRADE_NOT_NULL = 50014;

    public static final Integer PAGE_NOT_NULL = 50020;
    public static final Integer SIZE_NOT_NULL = 50021;

    public static final Integer IMAGE_NAME_NOT_NULL = 50031;
    public static final Integer IMAGE_FILE_NOT_NULL = 50032;
    public static final Integer PATH_CREATE_FAIL = 50033;
    public static final Integer FILE_INVALID = 50034;
    public static final Integer IMAGE_ID_INVALID = 50035;
    public static final Integer IMAGE_OVER_SIZE = 50036;

    public static final Integer TOKEN_INVALID = 50041;
    public static final Integer TOKEN_EMPTY = 50042;
    public static final Integer TOKEN_EXPIRED = 50043;

    public static final Integer PERMISSION_DENIED = 50050;


    public final static Map<Integer, String> MSG = new HashMap();
    static {
        MSG.put(SUCCESS, "操作成功！");
        MSG.put(FAIL, "操作失败！");

        MSG.put(LOGIN_FAIL, "帐号或密码错误！");
        MSG.put(LOGIN_SUCCESS, "登陆成功！");

        MSG.put(REGISTER_FAIL, "注册用户成功！");
        MSG.put(REGISTER_SUCCESS, "注册成功！");
        MSG.put(USER_EXIST, "用户已存在！");

        MSG.put(UPDATE_USER_SUCCESS, "修改用户成功！");
        MSG.put(UPDATE_USER_FAIL, "修改用户失败！");

        MSG.put(DELETE_USER_SUCCESS, "删除用户成功！");
        MSG.put(DELETE_USER_FAIL, "删除用户失败！");
        MSG.put(DELETE_USERS_FAIL, "未删除任何用户！");
        MSG.put(DELETE_USERS_SUCCESS, "总共删除了%s个用户！");

        MSG.put(ADD_IMAGE_FAIL, "添加图片失败！");
        MSG.put(ADD_IMAGE_SUCCESS, "添加图片成功！");

        MSG.put(DELETE_IMAGE_FAIL, "删除图片失败！");
        MSG.put(DELETE_IMAGE_SUCCESS, "删除图片成功！");
        MSG.put(DELETE_IMAGES_FAIL, "未删除任何图片！");
        MSG.put(DELETE_IMAGES_SUCCESS, "总共删除了%s张图片！");

        MSG.put(SELECT_IMAGE_FAIL, "没有该图片！");
        MSG.put(SELECT_IMAGE_SUCCESS, "查询图片成功！");

        MSG.put(UPDATE_IMAGE_FAIL, "修改图片失败！");
        MSG.put(UPDATE_IMAGE_SUCCESS, "修改图片成功！");

        MSG.put(EXCEPTION, "未知异常！");
        MSG.put(PARAMETER_ERR, "参数错误！");
        MSG.put(URL_NOT_EXIST, "访问路径不存在！");
        MSG.put(ACCESS_DENIED, "无文件夹管理权限！");


        MSG.put(USERNAME_NOT_NULL, "用户名不能为空！");
        MSG.put(PASSWORD_NOT_NULL, "密码不能为空！");
        MSG.put(GRADE_NOT_NULL, "权限参数不能为空！");
        MSG.put(ID_NOT_NULL, "id不能为空！");

        MSG.put(PAGE_NOT_NULL, "页面参数不能为空！");
        MSG.put(SIZE_NOT_NULL, "页面大小参数不能为空！");

        MSG.put(IMAGE_NAME_NOT_NULL, "图片名称不能为空！");
        MSG.put(IMAGE_FILE_NOT_NULL, "图片文件不能为空！");
        MSG.put(PATH_CREATE_FAIL, "上传文件目录创建失败！");
        MSG.put(FILE_INVALID, "无效的文件！");
        MSG.put(IMAGE_ID_INVALID, "请提供有效的图片id！");
        MSG.put(IMAGE_OVER_SIZE, "图片大小超过限制！");

        MSG.put(TOKEN_INVALID, "无效token！");
        MSG.put(TOKEN_EMPTY, "token不能为空！");
        MSG.put(TOKEN_EXPIRED, "token已经过期！");

        MSG.put(PERMISSION_DENIED, "没有访问权限！");

    }
}
