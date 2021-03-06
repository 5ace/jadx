package jadx.tests.integration.switches;

import jadx.core.dex.nodes.ClassNode;
import jadx.tests.api.IntegrationTest;

import org.junit.Test;

import static jadx.tests.api.utils.JadxMatchers.containsOne;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestSwitchInLoop extends IntegrationTest {
	public static class TestCls {
		public int test(int k) {
			int a = 0;
			while (true) {
				switch (k) {
					case 0:
						return a;
					default:
						a++;
						k >>= 1;
				}
			}
		}

		public void check() {
			assertEquals(1, test(1));
		}
	}

	@Test
	public void test() {
		ClassNode cls = getClassNode(TestCls.class);
		String code = cls.getCode().toString();

		assertThat(code, containsOne("switch (k) {"));
		assertThat(code, containsOne("case 0:"));
		assertThat(code, containsOne("return a;"));
		assertThat(code, containsOne("default:"));
		assertThat(code, containsOne("a++;"));
		assertThat(code, containsOne("k >>= 1;"));
	}
}
