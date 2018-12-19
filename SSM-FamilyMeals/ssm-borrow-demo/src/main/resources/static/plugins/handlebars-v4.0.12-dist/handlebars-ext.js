//扩展Handlebars功能，注意扩展功能代码写在Handlebars.compile(...)之前
//扩展功能一：相等运算符
Handlebars.registerHelper("eq", function(v1, v2, options){
    if(v1 == v2){
        //固定写法，满足if执行{{if}}部分
        return options.fn(this);
    }else{
        //不满足条件执行{{else}}部分
        return options.inverse(this);
    }
});
//扩展功能二：加法运算
Handlebars.registerHelper("add", function(v1, v2, options){
	return v1 + v2;
});
//扩展功能三：减法运算
Handlebars.registerHelper("minus", function(v1, v2, options){
	return v1 - v2;
});