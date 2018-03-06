/**        
 * 类名称：ClassAndMethodBean.java    
 * 类描述：    
 * 创建人：Arthur
 * 创建时间：2017年2月26日     
 * 备注：    
 * @version        
 */
package cn.ce.framework.core.tools;

import java.lang.reflect.Method;


public class ClassAndMethodBean {
    private Class<?> c;

    /**    
     * c    
     * @return  the c    
     */
    
    public Class<?> getC() {
        return c;
    }
    /**    
     * @param c the c to set    
     */
    public void setC(Class<?> c) {
        this.c = c;
    }
    public Method getM() {
        return m;
    }
    public void setM(Method m) {
        this.m = m;
    }
    private Method m;
}
