volley的使用

具体使用方法请参见:

http://blog.csdn.net/guolin_blog/article/details/17482095


1.volley的简单使用：

    ①创建一个RequestQueue（请求队列对象）：

        RequestQueue mqueue = Volley.newRequestQueue(context);

    ②创建一个request的请求

        //创建http请求
        StringRequest stringRequest = new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                simple_tv.setText(s);

            }

        }, new Response.ErrorListener() {
            //网络访问失败的处理
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.e("TAG",volleyError.getMessage(),volleyError);

            }
        });

       ③把request请求放入请求队列，访问网络：

        mqueue.add(stringRequest);

2.StringRequ的post方法测试

代码如下：
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://121.40.199.67/TrackServer/login",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Log.i("tag", "result:" +jsonObject.getString("result"));
                    Log.i("tag", "desc:" +jsonObject.getString("desc"));
                    Log.i("tag", "opid:" +jsonObject.getString("opid"));

                    volley_tv.setText(s);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new Hashtable<>();
                params.put("userName","123");
                params.put("passWord","8");

                return params;
            }
        };

说明Volley没有可以加载post参数的类，必须重写StringRequest类的getParams()方法来传递参数。

3.测试json，根据返回json类型选择相应的request请求主要有两种：JsonObjectRequest,JsonArrayRequest两种。

我认为这个最好只用作get请求，post请求会丢失标头。

具体代码如下：

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        json2_tv.setText(jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });