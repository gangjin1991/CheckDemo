package com.mrpeng.customviewdemo;

/**
 * <一句话功能描述>
 * <功能详细描述>
 *
 * @author pengjingnag
 * @see [相关类/方法]
 * @since [产品/模板版本]
 * @deprecated
 */
public class CheckUtil
{
    public static int[] getLine(int width, int height){
        int [] lineCoordinate=new int[4];
        for(int i=0;i<4;i+=2){
            lineCoordinate[i]= (int)(Math.random()*width);
            lineCoordinate[i+1]= (int)(Math.random()*height);
        }
        return lineCoordinate;
    }

    public static int getTextPosition(int height)
    {
        int tempPosition = (int)( Math.random()*height );
        if(tempPosition<0.5*height ){
            tempPosition+=0.5*height;
        }
        return tempPosition ;
    }

    public static int getPosition(int height)
    {
        int tempPosition = (int)( Math.random()*height );
        if(tempPosition<1){
            tempPosition+=1;
        }
        return tempPosition;
    }
}
