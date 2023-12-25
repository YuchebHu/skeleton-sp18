package proj1b;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
  @Test
  public void testOffByN() {
    CharacterComparator cc = new OffByN(5);
    assertFalse(cc.equalChars('a', 'b'));
    assertFalse(cc.equalChars('a', 'A'));
    assertTrue(cc.equalChars('a', 'f'));
  }
}
