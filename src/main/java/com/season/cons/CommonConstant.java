package com.season.cons;

/**
 *整个应用通用的常量 
 *<br><b>类描述:</b>
 *<pre>|</pre>
 *@see
 *@since
 */
public class CommonConstant
{
   /**
    * 用户对象放到Session中的键名称
    */
   public static final String USER_CONTEXT = "USER_CONTEXT";
   
   /**
    * 将登录前的URL放到Session中的键名称
    */
   public static final String LOGIN_TO_URL = "toUrl";
   
   /**
    * 每页的记录数
    */
   public static final int PAGE_SIZE = 15;

   public static final String BASE_PATH_LINUX = "/data/wwwfile/canteen/";

   public static final String BASE_PATH_WIN = "d://canteen/";

   public static final String[] PERSON_COUNT={"最近三天","最近一周","最近一个月","最近三个月"};//,"最近一年"

   public static final String[] MEAL_KIND={"早餐","午餐","晚餐","夜宵"};

   public static final String[] ORDER_STATUS={"已完成","未完成","已取消"};
}
