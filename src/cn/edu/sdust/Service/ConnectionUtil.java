package cn.edu.sdust.Service;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionUtil {
    /*
     * �ж����������Ƿ��ѿ�
     * 2012-08-20
     *true �Ѵ�  false δ��
     * */
    public static boolean isConn(Context context){
        boolean bisConnFlag=false;
        ConnectivityManager conManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = conManager.getActiveNetworkInfo();
        if(network!=null){
            bisConnFlag=conManager.getActiveNetworkInfo().isAvailable();
        }
        return bisConnFlag;
    }
    
    /*
     * �������������
     * */
    public static void setNetworkMethod(final Context context){
        //��ʾ�Ի���
        AlertDialog.Builder builder=new Builder(context);
        builder.setTitle("����������ʾ").setMessage("�������Ӳ�����,�Ƿ��������?").setPositiveButton("����", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Intent intent=null;
                //�ж��ֻ�ϵͳ�İ汾  ��API����10 ����3.0�����ϰ汾 
                if(android.os.Build.VERSION.SDK_INT>10){
                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                }else{
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);
            }
        }).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        }).show();
    }
    
    public static int getCurrentVersion(Activity activity) {
        try {
                // ��ȡӦ�ð���Ϣ
                PackageInfo info = activity.getPackageManager().getPackageInfo(
                                activity.getPackageName(), 0);
                return info.versionCode;
                //info.versionName;
        } catch (NameNotFoundException e) {
                e.printStackTrace();
                return 0;
        }
}
}