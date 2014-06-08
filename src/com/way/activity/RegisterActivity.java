package com.way.activity;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.way.util.PreferenceConstants;
import com.way.util.T;
import com.way.xx.R;
/**
 * 2014-05-21
 * @author li
 * 注册页面的实现
 */
public class RegisterActivity extends Activity {
	private Button mRegisterBtn;
	private EditText mRegisterPasswd1;
	private EditText mRegisterPasswd2;
	private EditText mRegisterAccount;
	private static  AccountManager mUserAccount;
	
    private static Connection connection;

    private static ConnectionConfiguration config;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registeraccount);
		init();
		initView();
		
	}
	protected void initView(){
		mRegisterBtn=(Button)findViewById(R.id.register_queding_btn);
		mRegisterAccount=(EditText)findViewById(R.id.register_zhanghao_edt);
		mRegisterPasswd1=(EditText)findViewById(R.id.register_mima_edt);
		mRegisterPasswd2=(EditText)findViewById(R.id.register_mima_queding_edt);
		mRegisterBtn.setFocusable(true);
		mRegisterBtn.setClickable(true);
		mRegisterBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				String passwd1=mRegisterPasswd1.getText().toString();
				String passwd2=mRegisterPasswd2.getText().toString();
				String account=mRegisterAccount.getText().toString();
				if(!passwd1.equals(passwd2)){
					T.showShort(RegisterActivity.this, "两次输入不同密码");
				}else if(passwd1.equals("")){
					T.showShort(RegisterActivity.this, "请输入密码");
				}else if(passwd1.length()<6){
					T.showShort(RegisterActivity.this, "密码长度小于6位");
				}else if(mRegisterAccount.length()<=0){
					T.showShort(RegisterActivity.this, "请输入帐号");
				}else{
					try {
						mUserAccount=connection.getAccountManager();
						mUserAccount.createAccount(account, passwd1);
					} catch (XMPPException e) {
						// TODO Auto-generated catch block
						T.showShort(RegisterActivity.this, "建立用户错误，请与li5jun@126.com联系");
						e.printStackTrace();
					}
				}
			}
		});
	}


    public void init() {

        try {

//            connection = new XMPPConnection(PreferenceConstants.Server);

//            connection.connect();

            /** 5222是openfire服务器默认的通信端口，你可以登录http://192.168.8.32:9090/到管理员控制台查看客户端到服务器端口 */

            config = new ConnectionConfiguration(PreferenceConstants.Server, 5222);

            

            /** 是否启用压缩 */ 

            config.setCompressionEnabled(true);

            /** 是否启用安全验证 */

            config.setSASLAuthenticationEnabled(true);

            /** 是否启用调试 */

            config.setDebuggerEnabled(false);

            config.setReconnectionAllowed(true);

            config.setRosterLoadedAtLogin(true);

            

            /** 创建connection链接 */

            connection = new XMPPConnection(config);

            /** 建立连接 */

            connection.connect();

        } catch (XMPPException e) {

            e.printStackTrace();

        }

        fail(connection);

        fail(connection.getConnectionID());

    }

    

private final void fail(Object o, Object... args) {

    if (o != null && args != null && args.length > 0) {

        String s = o.toString();

        for (int i = 0; i < args.length; i++) {

            String item = args[i] == null ? "" : args[i].toString();

            if (s.contains("{" + i + "}")) {

                s = s.replace("{" + i + "}", item);

            } else {

                s += " " + item;

            }

        }

        System.out.println(s);

    }

}


}
