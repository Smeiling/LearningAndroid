package com.smeiling.learning.drawable_md5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.R;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/app/drawable/hash")
public class DrawableHashActivity extends AppCompatActivity {

    @BindView(R.id.et_output)
    public EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_hash);
        ButterKnife.bind(this);

        StringBuilder hashBuilder = new StringBuilder();
        hashBuilder.append(getDrawableMD5(R.mipmap.esf_house_noimage_holder_1)).append("\n");

        hashBuilder.append(getDrawableMD5(R.mipmap.home_mask_1_16v9)).append("\n");
        hashBuilder.append(getDrawableMD5(R.mipmap.home_mask_1_18v9)).append("\n");
        hashBuilder.append(getDrawableMD5(R.mipmap.home_mask_2_16v9)).append("\n");
        hashBuilder.append(getDrawableMD5(R.mipmap.home_mask_2_18v9)).append("\n");
        hashBuilder.append(getDrawableMD5(R.mipmap.home_mask_3_16v9)).append("\n");
        hashBuilder.append(getDrawableMD5(R.mipmap.home_mask_3_18v9)).append("\n");

        editText.setText(hashBuilder.toString());

    }


    public String getDrawableMD5(int drawableId) {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), drawableId);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return MD5(baos.toByteArray());
    }


    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};

    private String MD5(byte[] buffer) {
        String s = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(buffer);
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换, >>>,
                // 逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            s = new String(str); // 换后的结果转换为字符串

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return s;
    }
}
