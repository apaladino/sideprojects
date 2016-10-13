package com.example.java.util.collecton;

import java.awt.RenderingHints;
import java.security.AuthProvider;
import java.security.Provider;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.jar.Attributes;

import javax.management.openmbean.TabularDataSupport;
import javax.management.openmbean.TabularType;
import javax.print.attribute.standard.PrinterStateReason;
import javax.print.attribute.standard.PrinterStateReasons;
import javax.print.attribute.standard.Severity;
import javax.script.SimpleBindings;
import javax.swing.UIDefaults;

public class MapExamples {

	private enum KEY_TYPES{
		ALPHA("alpha"),
		BETA("beta"),
		CHARLIE("charlie");
		
		private String type;
		
		KEY_TYPES(String t){
			this.type = t;
		}
	}
	
	public static void main(String [] a){
		new MapExamples().showExamples();
	}
	
	public void showExamples(){
		
		/*
		 * AbstractMap - This class provides a skeletal implementation of the Map interface, to minimize the effort required to implement this interface. 
		 */
		Map map = new CustomMap();
	
		/*
		 * Attributes - The Attributes class maps Manifest attribute names to associated string values. Valid attribute names are case-insensitive, are 
		 * 				restricted to the ASCII characters in the set [0-9a-zA-Z_-], and cannot exceed 70 characters in length. Attribute values can contain 
		 * 				any characters and will be UTF8-encoded when written to the output stream. See the JAR File Specification for more information about 
		 * 				valid attribute names and values.
		 */
		Attributes a = new Attributes();
		a.put("setting.firstname", "bob");
		
		/*
		 * AuthProvider - This class defines login and logout methods for a provider. While callers may invoke login directly, the provider may also invoke 
		 * 				  login on behalf of callers if it determines that a login must be performed prior to certain operations.
		 */
		AuthProvider authProvider = null;
		
		/*
		 * ConcurrentHashMap -  A hash table supporting full concurrency of retrievals and adjustable expected concurrency for updates. This class obeys the 
		 * 					    same functional specification as Hashtable, and includes versions of methods corresponding to each method of Hashtable. However, 
		 * 					    even though all operations are thread-safe, retrieval operations do not entail locking, and there is not any support for locking 
		 * 					    the entire table in a way that prevents all access. This class is fully interoperable with Hashtable in programs that rely on its 
		 * 						thread safety but not on its synchronization details.
		 */
		map = new ConcurrentHashMap();
		
		/*
		 * ConcurrentSkipListMap - 	A scalable concurrent ConcurrentNavigableMap implementation. The map is sorted according to the natural ordering of its keys, 
		 * 							or by a Comparator provided at map creation time, depending on which constructor is used. This class implements a concurrent 
		 * 							variant of SkipLists providing expected average log(n) time cost for the containsKey, get, put and remove operations and their 
		 * 							variants. Insertion, removal, update, and access operations safely execute concurrently by multiple threads. Iterators are 
		 * 							weakly consistent, returning elements reflecting the state of the map at some point at or since the creation of the iterator. 
		 * 							They do not throw ConcurrentModificationException, and may proceed concurrently with other operations. Ascending key ordered 
		 * 							views and their iterators are faster than descending ones. 
		 */
		map = new ConcurrentSkipListMap();
		
		/*
		 * EnumMap - 	A specialized Map implementation for use with enum type keys. All of the keys in an enum map must come from a single enum type that is specified, 
		 * 				explicitly or implicitly, when the map is created. Enum maps are represented internally as arrays. This representation is extremely compact 
		 * 				and efficient.
		 * 		
		 * 				Enum maps are maintained in the natural order of their keys (the order in which the enum constants are declared). This is reflected in the 
		 * 				iterators returned by the collections views (keySet(), entrySet(), and values()). 
		 */
		EnumMap<KEY_TYPES, String> enumMap = new EnumMap<>(KEY_TYPES.class);
		enumMap.put(KEY_TYPES.ALPHA, "alpha 1");
		enumMap.put(KEY_TYPES.BETA, "beta 1");
		
		/*
		 * HashMap - 	Hash table based implementation of the Map interface. This implementation provides all of the optional map operations, and permits null values 
		 * 				and the null key. (The HashMap class is roughly equivalent to Hashtable, except that it is unsynchronized and permits nulls.) This class makes 
		 * 				no guarantees as to the order of the map; in particular, it does not guarantee that the order will remain constant over time.
		 * 			
		 * 				This implementation provides constant-time performance for the basic operations (get and put), assuming the hash function disperses the elements 
		 * 				properly among the buckets. Iteration over collection views requires time proportional to the "capacity" of the HashMap instance (the number 
		 * 				of buckets) plus its size (the number of key-value mappings). Thus, it's very important not to set the initial capacity too high (or the load
		 * 				factor too low) if iteration performance is important.
		 */
		map = new HashMap();
		
		/*
		 * HashTable -	This class implements a hash table, which maps keys to values. Any non-null object can be used as a key or as a value. 
		 * 				
		 * 				To successfully store and retrieve objects from a hashtable, the objects used as keys must implement the hashCode method and the equals method.
		 */
		Hashtable<String,Integer> ht = new Hashtable<>();
		ht.put("one", 1);
		
		/*
		 * IdentityHashMap - 	This class implements the Map interface with a hash table, using reference-equality in place of object-equality when comparing keys 
		 * 						(and values). In other words, in an IdentityHashMap, two keys k1 and k2 are considered equal if and only if (k1==k2). (In normal Map 
		 * 						implementations (like HashMap) two keys k1 and k2 are considered equal if and only if (k1==null ? k2==null : k1.equals(k2)).) 
		 */
		map = new IdentityHashMap();
		
		/*
		 * LinkedHashMap -		Hash table and linked list implementation of the Map interface, with predictable iteration order. This implementation differs from 
		 * 						HashMap in that it maintains a doubly-linked list running through all of its entries. This linked list defines the iteration ordering, 
		 * 						which is normally the order in which keys were inserted into the map (insertion-order). Note that insertion order is not affected if a 
		 * 						key is re-inserted into the map. (A key k is reinserted into a map m if m.put(k, v) is invoked when m.containsKey(k) would return true 
		 * 						immediately prior to the invocation.)
		 */
		map = new LinkedHashMap();
		
		/*
		 * PrinterStateReasons -	Class PrinterStateReasons is a printing attribute class, a set of enumeration values, that provides additional information about 
		 * 							the printer's current state, i.e., information that augments the value of the printer's PrinterState attribute.
		 */
		PrinterStateReasons psr = new PrinterStateReasons();
		psr.put(PrinterStateReason.CONNECTING_TO_DEVICE, Severity.WARNING);
		
		/*
		 * Properties - 	The Properties class represents a persistent set of properties. The Properties can be saved to a stream or loaded from a stream. Each key 
		 * 					and its corresponding value in the property list is a string.
		 * 					A property list can contain another property list as its "defaults"; this second property list is searched if the property key is not 
		 * 					found in the original property list.
		 * 					Because Properties inherits from Hashtable, the put and putAll methods can be applied to a Properties object. Their use is strongly 
		 * 					discouraged as they allow the caller to insert entries whose keys or values are not Strings. The setProperty method should be used instead. 
		 * 					If the store or save method is called on a "compromised" Properties object that contains a non-String key or value, the call will fail. 
		 * 					Similarly, the call to the propertyNames or list method will fail if it is called on a "compromised" Properties object that contains a non-String key.
		 */
		Properties p = new Properties();
		p.setProperty("p1", "value1");
		
		/*
		 * Provider -	This class represents a "provider" for the Java Security API, where a provider implements some or all parts of Java Security. Services that a provider 
		 * 				may implement include:
		 * 					Algorithms (such as DSA, RSA, MD5 or SHA-1).
		 * 					Key generation, conversion, and management facilities (such as for algorithm-specific keys).
		 * 
		 *  			Each provider has a name and a version number, and is configured in each runtime it is installed in.
		 */
		Provider provider = null; //new Provider("Provider name", 1.0D, "Provider Description");
		
		/*
		 * RenderingHints - The RenderingHints class defines and manages collections of keys and associated values which allow an application to provide input into the 
		 * 					choice of algorithms used by other classes which perform rendering and image manipulation services. The Graphics2D class, and classes that 
		 * 					implement BufferedImageOp and RasterOp all provide methods to get and possibly to set individual or groups of RenderingHints keys and their 
		 * 					associated values. When those implementations perform any rendering or image manipulation operations they should examine the values of any 
		 * 					RenderingHints that were requested by the caller and tailor the algorithms used accordingly and to the best of their ability.
		 */
		RenderingHints rd = new RenderingHints(new HashMap());
		
		/*
		 * SimpleBindings - A simple implementation of Bindings backed by a HashMap or some other specified Map.
		 */
		SimpleBindings sb = new SimpleBindings();
		
		/*
		 * TabularDataSupport - The TabularDataSupport class is the open data class which implements the TabularData and the Map interfaces, and which is internally based
		 * 						on a hash map data structure.
		 */
		TabularDataSupport tds = new TabularDataSupport((TabularType) TabularType.ALLOWED_CLASSNAMES_LIST);
		
		/*
		 * TreeMap - A Red-Black tree based NavigableMap implementation. The map is sorted according to the natural ordering of its keys, or by a Comparator provided at map
		 * 			 creation time, depending on which constructor is used.
		 * 
		 * 			 This implementation provides guaranteed log(n) time cost for the containsKey, get, put and remove operations. 
		 */
		map = new TreeMap();
		
		/*
		 * UIDefaults - A table of defaults for Swing components. Applications can set/get default values via the UIManager.
		 * 
		 * 				Warning: Serialized objects of this class will not be compatible with future Swing releases. The current serialization support is appropriate for short 
		 * 				term storage or RMI	between applications running the same version of Swing. As of 1.4, support for long term storage of all JavaBeansTM has been added 
		 * 				to the java.beans package. Please see 
		 */
		
		UIDefaults uiDefaults = new UIDefaults();
		
		/*
		 * WeakHashMap - Hash table based implementation of the Map interface, with weak keys. An entry in a WeakHashMap will automatically be removed when its key is no longer 
		 * 				 in ordinary use. More precisely, the presence of a mapping for a given key will not prevent the key from being discarded by the garbage collector, that is, 
		 * 				 made finalizable, finalized, and then reclaimed. When a key has been discarded its entry is effectively removed from the map, so this class behaves 
		 * 				 somewhat differently from other Map implementations.
		 *  			 Like most collection classes, this class is not synchronized. A synchronized WeakHashMap may be constructed using the Collections.synchronizedMap method.
		 */
		map = new WeakHashMap();
		map.put("key", "value");
	}
	
	class CustomMap extends AbstractMap{

		private Map map;
		
		public CustomMap(){
			map = new HashMap();
		}
		
		@Override
		public Object put(Object key, Object value){
			return map.put(key, value);
		}
		
		@Override
		public Set entrySet() {
			
			Set<Map> entrySet = new HashSet<>();
			
			for(Object key : map.keySet()){
				entrySet.add(Collections.singletonMap(key, map.get(key)));
			}
			return entrySet;
		}
		
		// ... TODO: implement more overrides
	}
}
