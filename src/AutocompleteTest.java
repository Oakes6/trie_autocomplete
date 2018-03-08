import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;


public class AutocompleteTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   // Adding first word "all" w/ weight of 3.
   @Test public void firstTest() {
   
      String[] terms = {"all"};
      double[] weight = {3};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      Node root = trie.myRoot;
      
      Assert.assertEquals("root test", "-", root.myInfo);
      Assert.assertEquals("root test", 3, root.mySubtreeMaxWeight, 1);
      
      String expectedInfo = "a";
      Assert.assertEquals("child of root", expectedInfo, root.getChild('a').myInfo);
      
      double expectedSubtreeMaxWeight = 3;
      Assert.assertEquals("subtreeMaxWeight for first child", expectedSubtreeMaxWeight, root.getChild('a').mySubtreeMaxWeight, .01);
      
      double expectedWeight = -1;
      Assert.assertEquals("weight for non-ending char node", expectedWeight, root.getChild('a').getWeight(), .01);
          
   }
   
   // Another test for "all".
   @Test public void secondTest() {
      
      String[] terms = {"all"};
      double[] weight = {3};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      Node root = trie.myRoot;
      Node a = root.getChild('a');
      
      String expectedInfo = "l";
      Assert.assertEquals("info for second child", expectedInfo, a.getChild('l').myInfo);
      
      double expectedSubtreeMaxWeight = 3;
      Assert.assertEquals("subtreeMaxWeight for second child", expectedSubtreeMaxWeight, a.getChild('l').mySubtreeMaxWeight, .01);
      
      double expectedWeight = -1;
      Assert.assertEquals("weight for non-ending char node", expectedWeight, a.getChild('l').getWeight(), .01);
      
      boolean expectedBool = false;
      Assert.assertEquals("not a word", expectedBool, a.getChild('l').isWord);
      
      
   }
   
   // Another test for "all".
   @Test public void thirdTest() {
      
      String[] terms = {"all"};
      double[] weight = {3};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      Node root = trie.myRoot;
      Node a = root.getChild('a');
      Node l = a.getChild('l');
      
      String expectedInfo = "l";
      Assert.assertEquals("info for ending word", expectedInfo, l.getChild('l').myInfo);
      
      double expectedSubtreeMaxWeight = 3;
      Assert.assertEquals("subtreeMaxWeight for first child", expectedSubtreeMaxWeight, l.getChild('l').mySubtreeMaxWeight, .01);
      
      double expectedWeight = 3;
      Assert.assertEquals("weight for non-ending char node", expectedWeight, l.getChild('l').getWeight(), .01);
      
      boolean expectedWordBool = true;
      Assert.assertEquals("bool for ending word node", expectedWordBool, l.getChild('l').isWord);
      
      String expectedWord = "all";
      Assert.assertEquals("ending word", expectedWord, l.getChild('l').getWord());
      
      Node l2 = l.getChild('l');
      boolean expectedCap = true;
      Assert.assertEquals("no children after last node test", expectedCap, l2.children.isEmpty());
   }
   
   // Adding second word "bat" w/ weight 2.
   @Test public void fourthTest() {
      
      String[] terms = {"all", "bat"};
      double[] weight = {3, 2};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      Node root = trie.myRoot;
      Node b = root.getChild('b');
      
      double expectedSubtreeMaxWeightRoot = 3;
      Assert.assertEquals("subtreeMaxWeight for root", expectedSubtreeMaxWeightRoot, root.mySubtreeMaxWeight, .01);
      
      String expectedInfo = "b";
      Assert.assertEquals("info for first child", expectedInfo, b.myInfo);
      
      double expectedWeight = -1;
      Assert.assertEquals("weight for non-ending char node", expectedWeight, b.getWeight(), .01);
      
      double expectedSubtreeMaxWeight = 2;
      Assert.assertEquals("weight for non-ending char node", expectedSubtreeMaxWeight, b.mySubtreeMaxWeight, .01);
      
      boolean expectedWordBool = false;
      Assert.assertEquals("bool for first child node", expectedWordBool, b.isWord);
      
      String expectedWord = null;
      Assert.assertEquals("non-ending word", expectedWord, b.getWord());
   }
   
   //Another test for adding "bat"
   @Test public void fifthTest() {
   
      String[] terms = {"all", "bat"};
      double[] weight = {3, 2};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      Node root = trie.myRoot;
      Node b = root.getChild('b');
      Node a = b.getChild('a');
      Node t = a.getChild('t');
      
      boolean isWordExpectedBool = true;
      Assert.assertEquals("isWord", isWordExpectedBool, t.isWord);
      
      String expectedWord = "bat";
      Assert.assertEquals("word at last node", expectedWord, t.getWord());
      
      double expectedweight = 2;
      Assert.assertEquals("weight at last node", expectedweight, t.getWeight(), .01);
      
      double expectedSubtreeMaxWeight = 2;
      Assert.assertEquals("subtree max weight at last node", expectedSubtreeMaxWeight, t.mySubtreeMaxWeight, .01);
      
      boolean expectedCap = true;
      Assert.assertEquals("no children after last node test", expectedCap, t.children.isEmpty());
      
      //check for 2 nodes from root
      int expectedSize = 2;
      Assert.assertEquals("2 words in tree", expectedSize, root.children.size());
      
      int expectedSize2 = 1;
      Assert.assertEquals("1 child after first key picked", expectedSize2, b.children.size());
      
      Node a2 = root.getChild('a');
      int expectedSize3 = 1;
      Assert.assertEquals("1 child after first key picked", expectedSize3, a2.children.size());
   }
   
   // Adding third word "apes" w/ weight 5
   @Test public void sixthTest() {
   
      String[] terms = {"all", "bat", "apes"};
      double[] weight = {3, 2, 5};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      Node root = trie.myRoot;
      Node a = root.getChild('a');
      Node p = a.getChild('p');
      Node e = p.getChild('e');
      Node s = e.getChild('s');
      
      //testing root
      double expectedSubtreeMaxWeightRoot = 5;
      Assert.assertEquals("root updates sub weight", expectedSubtreeMaxWeightRoot, root.mySubtreeMaxWeight, .01);
      
      //testing first child
      double expectedSubtreeMaxWeightA = 5;
      Assert.assertEquals("child 'a' updates to 5", expectedSubtreeMaxWeightA, a.mySubtreeMaxWeight, .01);
      
      //test the split after 'a'... words "all" and "apes" split off here
      double expectedSubtreeMaxWeightP = 5;
      Assert.assertEquals("child p sub weight", expectedSubtreeMaxWeightP, p.mySubtreeMaxWeight, .01);
      
      Node l = a.getChild('l');
      double expectedSubtreeMaxWeightL = 3;
      Assert.assertEquals("child l sub weight", expectedSubtreeMaxWeightL, l.mySubtreeMaxWeight, .01);
      
      //test the final node
      double expectedWordWeight = 5;
      Assert.assertEquals("final node update", expectedWordWeight, s.getWeight(), .01);
      
      double expectedFinalSubWeight = 5;
      Assert.assertEquals("final node update", expectedFinalSubWeight, s.mySubtreeMaxWeight, .01);
      
   }
   
   // ReAdding "apes" w/ weight 6
   @Test public void seventhTest() {
   
      String[] terms = {"all", "bat", "apes", "apes"};
      double[] weight = {3, 2, 5, 6};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      Node root = trie.myRoot;
      Node a = root.getChild('a');
      Node p = a.getChild('p');
      Node e = p.getChild('e');
      Node s = e.getChild('s');
      
      //testing root
      double expectedSubtreeMaxWeightRoot = 6;
      Assert.assertEquals("root updates sub weight", expectedSubtreeMaxWeightRoot, root.mySubtreeMaxWeight, .01);
      
      //testing first child
      double expectedSubtreeMaxWeightA = 6;
      Assert.assertEquals("child 'a' updates to 5", expectedSubtreeMaxWeightA, a.mySubtreeMaxWeight, .01);
      
      //test the split after 'a'... words "all" and "apes" split off here
      double expectedSubtreeMaxWeightP = 6;
      Assert.assertEquals("child p sub weight", expectedSubtreeMaxWeightP, p.mySubtreeMaxWeight, .01);
      
      Node l = a.getChild('l');
      double expectedSubtreeMaxWeightL = 3;
      Assert.assertEquals("child l sub weight", expectedSubtreeMaxWeightL, l.mySubtreeMaxWeight, .01);
      
      //test the end of the word to check for updated sub and reg weight
      double expectedWordWeight = 6;
      Assert.assertEquals("final node update", expectedWordWeight, s.getWeight(), .01);
      
      double expectedFinalSubWeight = 6;
      Assert.assertEquals("final node update", expectedFinalSubWeight, s.mySubtreeMaxWeight, .01);
      
      //test the split off from root
      Assert.assertEquals("children of root size", 2, root.children.size());
      Node b = root.getChild('b');
      Assert.assertEquals("test for 'b'", 2, b.mySubtreeMaxWeight, .01);
   }
   
   // ReAdding "apes" once again but w/ weight 4
   @Test public void eighthTest() {
   
      String[] terms = {"all", "bat", "apes", "apes", "apes"};
      double[] weight = {3, 2, 5, 6, 2};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      Node root = trie.myRoot;
      Node a = root.getChild('a');
      Node p = a.getChild('p');
      Node e = p.getChild('e');
      Node s = e.getChild('s');
      
      //testing root
      double expectedSubtreeMaxWeightRoot = 3;
      Assert.assertEquals("root updates sub weight", expectedSubtreeMaxWeightRoot, root.mySubtreeMaxWeight, .01);
      
      //testing first child
      double expectedSubtreeMaxWeightA = 3;
      Assert.assertEquals("child 'a' updates to 5", expectedSubtreeMaxWeightA, a.mySubtreeMaxWeight, .01);
      
      //test the split after 'a'... words "all" and "apes" split off here
      double expectedSubtreeMaxWeightP = 2;
      Assert.assertEquals("child p sub weight", expectedSubtreeMaxWeightP, p.mySubtreeMaxWeight, .01);
      
      Node l = a.getChild('l');
      double expectedSubtreeMaxWeightL = 3;
      Assert.assertEquals("child l sub weight", expectedSubtreeMaxWeightL, l.mySubtreeMaxWeight, .01);
      
      //test the end of the word to check for updated sub and reg weight
      double expectedWordWeight = 2;
      Assert.assertEquals("final node update", expectedWordWeight, s.getWeight(), .01);
      
      double expectedFinalSubWeight = 2;
      Assert.assertEquals("final node update", expectedFinalSubWeight, s.mySubtreeMaxWeight, .01);
      Assert.assertEquals("final node check", 2, s.getWeight(), .01);
      
      //test the split off from root
      Assert.assertEquals("children of root size", 2, root.children.size());
      Node b = root.getChild('b');
      Assert.assertEquals("test for 'b'", 2, b.mySubtreeMaxWeight, .01);
   }
   
   // Test for defining a prefix of a longer word as a word
   @Test public void ninthTest() {
   
      String[] terms = {"all", "bat", "apes", "ape"};
      double[] weight = {3, 2, 5, 1};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      Node root = trie.myRoot;
      Node a = root.getChild('a');
      Node l = a.getChild('l');
      Node p = a.getChild('p');
      Node e = p.getChild('e');
      Node s = e.getChild('s');
      
      // test the root
      Assert.assertEquals("root sub weight", 5, root.mySubtreeMaxWeight, .01);
      
      // test 'a' for children and their values
      Assert.assertEquals("# of 'a' children", 2, a.children.size());
      Assert.assertEquals("val of 'l' sub weight", 3, l.mySubtreeMaxWeight, .01);
      Assert.assertEquals("val of 'p' sub weight", 5, p.mySubtreeMaxWeight, .01);
      
      //test 'e' for word and weights
      Assert.assertEquals("is 'e' word", true, e.isWord);
      Assert.assertEquals("'e' weight", 1, e.getWeight(), .01);
      Assert.assertEquals("'e' word", "ape", e.myWord);
      Assert.assertEquals("'e' sub weight", 5, e.mySubtreeMaxWeight, .01); 
      
      //test 's' for word and weights
      Assert.assertEquals("is 's' word", true, s.isWord);
      Assert.assertEquals("'s' weight", 5, s.getWeight(), .01);
      Assert.assertEquals("'s' sub weight", 5, s.mySubtreeMaxWeight, .01);
   }
   
   // testing for tree construction
   @Test public void tenthTest() {
   
      String[] terms = {"all", "bat", "apes", "ape", "bay"};
      double[] weight = {3, 2, 5, 1, 4};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      Node root = trie.myRoot;
      Node b = root.getChild('b');
      Node a = b.getChild('a');
      Node t = a.getChild('t');
      Node y = a.getChild('y');
      
      //testing top match
      Assert.assertEquals("tree split", true, t.isWord);
      Assert.assertEquals("tree split", true, y.isWord);
      Assert.assertEquals("tree split", "bat", t.myWord);
      Assert.assertEquals("tree split", "bay", y.myWord);
      Assert.assertEquals("tree split", false, a.isWord);      
   }
   
   // testing for topmatch
   @Test public void topMatchTest1() {
   
      String[] terms = {"all", "bat", "apes"};
      double[] weight = {3, 2, 5};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      
      //testing top match
      Assert.assertEquals("top match", "apes", trie.topMatch("ape"));
      
      Assert.assertEquals("top match", "bat", trie.topMatch("ba"));
      
      Assert.assertEquals("top match", "all", trie.topMatch("all"));
      
      
   }
   
   // testing for topmatch
   @Test public void topMatchTest2() {
   
      String[] terms = {"all", "bat", "apes", "ape", "bay"};
      double[] weight = {3, 2, 5, 1, 4};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      
      //testing top match
      Assert.assertEquals("top match", "bay", trie.topMatch("ba"));
      
      Assert.assertEquals("top match", "bat", trie.topMatch("bat"));
      
      Assert.assertEquals("top match", "apes", trie.topMatch("a"));
      
      Assert.assertEquals("top match", "all", trie.topMatch("al"));
      
   }
   
   // testing for topmatch
   @Test public void topMatchTest3() {
   
      String[] terms = {"all", "bat", "apes", "ape", "bay"};
      double[] weight = {3, 2, 3, 4, 4};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      
      //testing top match      
      Assert.assertEquals("top match", "ape", trie.topMatch("a"));
      Assert.assertEquals("top match", "ape", trie.topMatch("ape"));
      Assert.assertEquals("top match", "apes", trie.topMatch("apes"));
      
   }
   
   // testing for topMatches
   @Test public void topMatchesTest1() {
      String[] terms = {"all", "bat", "apes", "ape", "bay"};
      double[] weight = {3, 2, 5, 3, 4};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      
      //testing topMatches
      ArrayList<String> expected = new ArrayList<String>();
      expected.add("bay");
      expected.add("bat");
      Assert.assertEquals("top matches", expected, trie.topMatches("b", 2));
      
      //testing topMatches
      ArrayList<String> expected2 = new ArrayList<String>();
      expected2.add("apes");
      expected2.add("all");
      expected2.add("ape");
      Assert.assertEquals("top matches", expected2, trie.topMatches("a", 3));
      
      //testing topMatches
      ArrayList<String> expected3 = new ArrayList<String>();
      expected3.add("apes");
      expected3.add("ape");
      Assert.assertEquals("top matches", expected3, trie.topMatches("ap", 2));
      
      //testing topMatches
      ArrayList<String> expected4 = new ArrayList<String>();
      expected4.add("apes");
      Assert.assertEquals("top matches", expected4, trie.topMatches("ap", 1));
      
      //testing topMatches
      ArrayList<String> expected5 = new ArrayList<String>();
      expected5.add("all");
      Assert.assertEquals("top matches", expected5, trie.topMatches("all", 1));
   }   
   
   // weightOf tests
   @Test public void weightOfTest() {
      String[] terms = {"all", "bat", "apes", "ape", "bay"};
      double[] weight = {3, 2, 5, 3, 4};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      
      Assert.assertEquals("weightOf", 3, trie.weightOf("all"), 0.1);
      Assert.assertEquals("weightOf", 0.0, trie.weightOf("al"), 0.1);
      Assert.assertEquals("weightOf", 4, trie.weightOf("bay"), 0.1);
      Assert.assertEquals("weightOf", 3, trie.weightOf("ape"), 0.1);
   }
   
   // deliverables test
   @Test public void deverablesTests() {
      String[] terms = {"ape", "app", "ban", "bat", "bee", "car", "cat"};
      double[] weight = {6, 4, 2, 3, 5, 7, 1};
      Autocomplete.TrieAutocomplete trie = new Autocomplete.TrieAutocomplete(terms, weight);
      
      Assert.assertEquals("car", trie.topMatch(""));
      Assert.assertEquals("ape", trie.topMatch("a"));
      Assert.assertEquals("ape", trie.topMatch("ap"));
      Assert.assertEquals("bee", trie.topMatch("b"));
      Assert.assertEquals("bat", trie.topMatch("ba"));
      Assert.assertEquals("car", trie.topMatch("c"));
      Assert.assertEquals("car", trie.topMatch("ca"));
      Assert.assertEquals("cat", trie.topMatch("cat"));
      Assert.assertEquals("", trie.topMatch("d"));
      Assert.assertEquals("", trie.topMatch(" "));
      
      ArrayList<String> l = new ArrayList<String>();
      l.add("car");
      l.add("ape");
      l.add("bee");
      l.add("app");
      l.add("bat");
      l.add("ban");
      l.add("cat");
      Assert.assertEquals(l, trie.topMatches("", 8));
      
      ArrayList<String> l2 = new ArrayList<String>();
      l2.add("car");
      Assert.assertEquals(l2, trie.topMatches("", 1));
      
      ArrayList<String> l3 = new ArrayList<String>();
      l3.add("car");
      l3.add("ape");
      Assert.assertEquals(l3, trie.topMatches("", 2));
      
      ArrayList<String> l4 = new ArrayList<String>();
      l4.add("car");
      l4.add("ape");
      l4.add("bee");
      Assert.assertEquals(l4, trie.topMatches("", 3));
      
      ArrayList<String> l5 = new ArrayList<String>();
      l5.add("ape");
      Assert.assertEquals(l5, trie.topMatches("a", 1));
      
      ArrayList<String> l6 = new ArrayList<String>();
      l6.add("ape");
      Assert.assertEquals(l6, trie.topMatches("ap", 1));
      
      ArrayList<String> l7 = new ArrayList<String>();
      l7.add("bee");
      l7.add("bat");
      Assert.assertEquals(l7, trie.topMatches("b", 2));
      
      ArrayList<String> l8 = new ArrayList<String>();
      l8.add("bat");
      l8.add("ban");
      Assert.assertEquals(l8, trie.topMatches("ba", 2));
      
      ArrayList<String> l9 = new ArrayList<String>();
      Assert.assertEquals(l9, trie.topMatches("d", 100));
   }
   
   public ArrayList<ArrayList<Term>> allPermutes(ArrayList<Term> arr){
		if (arr.size() == 1){
			ArrayList<ArrayList<Term>> output = new
					ArrayList<ArrayList<Term>>();
			output.add(arr);
			return output;
		}
		ArrayList<ArrayList<Term>> output = 
				new ArrayList<ArrayList<Term>>();
		for(int i = 0; i < arr.size(); i++){
			ArrayList<Term> arrcopy = new ArrayList<Term>(arr);
			arrcopy.remove(i);
			ArrayList<ArrayList<Term>> subPermutes = allPermutes(arrcopy);
			for(ArrayList<Term> permute: subPermutes)
				permute.add(arr.get(i));
			output.addAll(subPermutes);
		}
		return output;
	}
   
   @Test(timeout = 10000)
	public void testAdd() {
		ArrayList<Term> termList = new ArrayList<Term>();
		Term[] terms =
				new Term[] {new Term("ape", 6), 
				new Term("app", 4), 
				new Term("ban", 2),
				new Term("bat", 3),
				new Term("bee", 5),
				new Term("car", 7),
				new Term("cat", 1)};
		String[] queries = {"", "a", "ap", "ape", "app", "b", "ba", "ban", 
				"bat", "be", "bee",	"c", "ca", "car", "cat", "f"};
		for(Term t: terms)
			termList.add(t);
		ArrayList<ArrayList<Term>> orders = allPermutes(termList);
		HashSet<ArrayList<String>> outputs = 
				new HashSet<ArrayList<String>>();
		for(ArrayList<Term> order: orders){
			String[] names = new String[order.size()];
			double[] weights = new double[order.size()];
			for(int i = 0; i < order.size(); i++){
				names[i] = order.get(i).getWord();
				weights[i] = order.get(i).getWeight();
			}
			Autocomplete.TrieAutocomplete auto = new Autocomplete.TrieAutocomplete(names, weights);
			ArrayList<String> output = new ArrayList<String>();
			for(String query: queries){
				output.add(auto.topMatch(query));
			}
			outputs.add(output);
			assertTrue("results depend on add order",
					outputs.size() <= 1);
		}
	}
}
