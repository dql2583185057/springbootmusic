package com.example.springbootmusic;

import com.example.web.util.EncryptUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootmusicApplicationTests {

	@Test
	public void contextLoads() {
	}


	/**
	 *  测试方法
	 */
	@Test
	public void test() throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("encSecKey","39c3ef05130d29fe5087a8f34c2bad83a5ba55c469fad98f4bf3259e78f666f503f6e51a15042baf254ed8df00712515b73773882256b0bfd06cad5c6f7152b400ecbfbbd8577bab0b4b32fe3f317242dd265a3397107914d142cf9a7a2c26c76888bbc7dd77254d1b627c1dff218d770aead987fdfe141c9dd527bee691e7f0");
		params.put("params","Zff/QUCciAB3bqAZIAbjAyA7umXyUkoLWi4jiO/elGeGx/DtZEdV0cnVhdtvMHZtEyGfdtxmnz7phoD/xL3/AXrwN4eSKVFkrPwzublQnlU=");
		Connection.Response
				response = Jsoup.connect("https://music.163.com/weapi/song/enhance/player/url?csrf_token=")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
				.header("Accept", "*/*")
				.header("Accept-Encoding", "gzip, deflate, br")
				.header("Accept-Language", "zh-CN,zh;q=0.9")
				.header("Content-Length", "408")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.header("Cookie", "_iuqxldmzr_=32; _ntes_nnid=9a70f3201fcbd6cf4edb02ba394a65c5,1531897058172; _ntes_nuid=9a70f3201fcbd6cf4edb02ba394a65c5; __remember_me=true; usertrack=ezq0o1tsBG6FVe1fBHrBAg==; _ga=GA1.2.1958241620.1533805677; UM_distinctid=16555e96742221-0bc5c3f6fc14a2-9393265-1fa400-16555e96744b4f; vjuids=-89d787cd4.16555e96924.0.e0fc8f49344fa; vjlast=1534744685.1534744685.30; __gads=ID=d7c480b9588b599f:T=1534744686:S=ALNI_Mbb9QcmO1jlX3On_eF82v6dwLy7gg; vinfo_n_f_l_n3=d99ca1905f8ab07f.1.0.1534744684876.0.1534744691814; __utmc=94650624; WM_TID=ymHXXAelaYj3mXYLUXlOTa5z2N%2B8Wk%2B6; __utmz=94650624.1535686730.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); playerid=46149715; JSESSIONID-WYYY=vcZkU3tA2do87BcvX3NOhUIoP6X%2F6Y9c%2FNUlRYpgUF1%5CIAoUpqTy%2B%5CiuVQOxJV8%2BvXO5VWIAAHDzEB%2BG0fZbRDjzIAtMtG8Wc%5CwXF8Y6FWu19Ajs0%5CjdGpPk0M6wkyrmrDhqTe7idwY452wHPkR3oEUYR0CSmWh1eCBEk%2F1N1ye8Cprn%3A1535693748773; WM_NI=i%2B%2BfZIkp2EH5%2BKEJYTld29ezN9qBqTP%2B3RQ2KF1J93rVMarCp%2BXjakOk2vKmUa%2FduI3lHsMxer2sp99K4uKDg9Hkd6lEiUpufIvdUvdCam64ZTjaYu8wLE4x1Nb82UVOS2I%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6eed3d85986ea8cd3c7798f8ba888d45296bdb9afcc6490aaff82ce42bbae989bb42af0fea7c3b92aabf5e587d943a9b0fea6cb3fe9958babdc60b0bcfedae8728ee7bfa3f46094f5fdb3d43a899affabbc39b28a8382f149b4f5988be4618db5fe97ca6797bfe185f843f1b48894d074a7aefe94f333fb95fc96ca50a5e7888ce73eb09ba7b6f37fa697ba85d87ea89daf8eb5409a8ca682dc7ffca984a6b774f392aed9b15f96919dd3cc37e2a3; __utma=94650624.1958241620.1533805677.1535686730.1535693520.2; __utmb=94650624.2.10.1535693520")
				.header("Connection", "keep-alive")
				.header("Host", "music.163.com")
				.header("Origin", "https://music.163.com")
				.header("Referer", "https://music.163.com/")
				.data(params)
				.method(Connection.Method.POST)
				.ignoreContentType(true)
				.timeout(10000)
				.execute();
		String list = response.body();
		System.out.println("返回结果值："+response.statusCode());
		System.out.println("返回结果："+list);


	}
}
