/* 
 * Copyright © 2011 Karl J. Ots <kjots@kjots.org>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kjots.json.content.shared;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import org.mockito.InOrder;
import org.mockito.Mock;

import org.kjots.json.content.JsonContentTestBase;

/**
 * JSON Content Util Test.
 * <p>
 * Created: 25th February 2011.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.1
 */
public class JsonContentUtilTest extends JsonContentTestBase {
  /** The mock JSON content handler. */
  @Mock
  private JsonContentHandler mockJsonContentHandler;
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Object) method
   * with a <code>null</code> value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testHandleWithNullValue() {
    JsonContentUtil.handle(mockJsonContentHandler, (Object)null);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Object, boolean)
   * method with a <code>null</code> value and <code>fragment<code> set to
   * <code>true</code>.
   */
  @Test
  public void testHandleWithNullValueAsFragment() {
    JsonContentUtil.handle(mockJsonContentHandler, (Object)null, true);
    
    verify(mockJsonContentHandler).primitive(null);
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Object) method
   * with a {@link Boolean} value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testHandleWithBooleanValue() {
    JsonContentUtil.handle(mockJsonContentHandler, Boolean.TRUE);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Object, boolean)
   * method with a {@link Boolean} value and <code>fragment<code> set to
   * <code>true</code>.
   */
  @Test
  public void testHandleWithBooleanValueAsFragment() {
    JsonContentUtil.handle(mockJsonContentHandler, Boolean.TRUE, true);
    
    verify(mockJsonContentHandler).primitive(Boolean.TRUE);
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Object) method
   * with a {@link Number} value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testHandleWithNumberValue() {
    JsonContentUtil.handle(mockJsonContentHandler, Double.valueOf(3.14));
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Object, Number)
   * method with a {@link Number} value and <code>fragment<code> set to
   * <code>true</code>.
   */
  @Test
  public void testHandleWithNumberValueAsFragment() {
    JsonContentUtil.handle(mockJsonContentHandler, Double.valueOf(3.14), true);
    
    verify(mockJsonContentHandler).primitive(Double.valueOf(3.14));
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Object) method
   * with a {@link String} value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testHandleWithStringValue() {
    JsonContentUtil.handle(mockJsonContentHandler, "Test String Value");
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Object, String)
   * method with a {@link String} value and <code>fragment<code> set to
   * <code>true</code>.
   */
  @Test
  public void testHandleWithStringValueAsFragment() {
    JsonContentUtil.handle(mockJsonContentHandler, "Test String Value", true);
    
    verify(mockJsonContentHandler).primitive("Test String Value");
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Object) method
   * with an unsupported object value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testHandleWithUnsupportedObjectValue() {
    JsonContentUtil.handle(mockJsonContentHandler, new Object());
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Object, String)
   * method with an unsupported object value and <code>fragment<code> set to
   * <code>true</code>.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testHandleWithUnsupportedObjectAsFragment() {
    JsonContentUtil.handle(mockJsonContentHandler, new Object(), true);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with an empty map.
   */
  @Test
  public void testHandleEmptyMap() {
    JsonContentUtil.handle(mockJsonContentHandler, Collections.<String, Object>emptyMap());
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map, boolean)}
   * method with an empty map and <code>fragment<code> set to <code>true</code>.
   */
  @Test
  public void testHandleEmptyMapAsFragment() {
    JsonContentUtil.handle(mockJsonContentHandler, Collections.<String, Object>emptyMap(), true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).endObject();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with a <code>null</code> entry.
   */
  @Test
  public void testHandleMapWithNullEntry() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", null);
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("Test Member Name");
    inOrder.verify(mockJsonContentHandler).primitive(null);
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with a <code>null</code> entry and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test
  public void testHandleMapWithNullEntryAsFragment() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", null);
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap, true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("Test Member Name");
    inOrder.verify(mockJsonContentHandler).primitive(null);
    inOrder.verify(mockJsonContentHandler).endObject();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with a {@link Boolean} entry.
   */
  @Test
  public void testHandleMapWithBooleanEntry() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", Boolean.TRUE);
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("Test Member Name");
    inOrder.verify(mockJsonContentHandler).primitive(Boolean.TRUE);
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with a {@link Boolean} entry and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test
  public void testHandleMapWithBooleanEntryAsFragment() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", Boolean.TRUE);
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap, true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("Test Member Name");
    inOrder.verify(mockJsonContentHandler).primitive(Boolean.TRUE);
    inOrder.verify(mockJsonContentHandler).endObject();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with a {@link Number} entry.
   */
  @Test
  public void testHandleMapWithNumberEntry() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", Double.valueOf(3.14));
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("Test Member Name");
    inOrder.verify(mockJsonContentHandler).primitive(Double.valueOf(3.14));
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with a {@link Number} entry and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test
  public void testHandleMapWithNumberEntryAsFragment() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", Double.valueOf(3.14));
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap, true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("Test Member Name");
    inOrder.verify(mockJsonContentHandler).primitive(Double.valueOf(3.14));
    inOrder.verify(mockJsonContentHandler).endObject();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with a {@link String} entry.
   */
  @Test
  public void testHandleMapWithStringEntry() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", "Test String Value");
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("Test Member Name");
    inOrder.verify(mockJsonContentHandler).primitive("Test String Value");
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with a {@link String} entry and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test
  public void testHandleMapWithStringEntryAsFragment() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", "Test String Value");
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap, true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("Test Member Name");
    inOrder.verify(mockJsonContentHandler).primitive("Test String Value");
    inOrder.verify(mockJsonContentHandler).endObject();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with an unsupported object value entry.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testHandleMapWithUnsupportedObjectEntry() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", new Object());
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with an unsupported object value entry and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testHandleMapWithUnsupportedObjectEntryAsFragment() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", new Object());
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap, true);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with a {@link Map} entry.
   */
  @Test
  public void testHandleMapWithMapEntry() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", Collections.emptyMap());
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("Test Member Name");
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler, times(2)).endObject();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with a {@link Map} entry and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test
  public void testHandleMapWithMapEntryAsFragment() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", Collections.emptyMap());
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap, true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("Test Member Name");
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler, times(2)).endObject();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with a {@link List} entry.
   */
  @Test
  public void testHandleMapWithListEntry() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", Collections.emptyList());
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("Test Member Name");
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).endArray();
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Map)}
   * method with a list with a {@link List} entry and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test
  public void testHandleMapWithListEntryAsFragment() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("Test Member Name", Collections.emptyList());
    
    JsonContentUtil.handle(mockJsonContentHandler, testMap, true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("Test Member Name");
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).endArray();
    inOrder.verify(mockJsonContentHandler).endObject();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with an empty list.
   */
  @Test
  public void testHandleEmptyList() {
    JsonContentUtil.handle(mockJsonContentHandler, Collections.emptyList());
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).endArray();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List, boolean)}
   * method with an empty list and <code>fragment<code> set to <code>true</code>.
   */
  @Test
  public void testHandleEmptyListAsFragment() {
    JsonContentUtil.handle(mockJsonContentHandler, Collections.emptyList(), true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).endArray();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with a <code>null</code> element.
   */
  @Test
  public void testHandleListWithNullElement() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add(null);
    
    JsonContentUtil.handle(mockJsonContentHandler, testList);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).primitive(null);
    inOrder.verify(mockJsonContentHandler).endArray();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with a <code>null</code> element and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test
  public void testHandleListWithNullElementAsFragment() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add(null);
    
    JsonContentUtil.handle(mockJsonContentHandler, testList, true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).primitive(null);
    inOrder.verify(mockJsonContentHandler).endArray();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with a {@link Boolean} element.
   */
  @Test
  public void testHandleListWithBooleanElement() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add(Boolean.TRUE);
    
    JsonContentUtil.handle(mockJsonContentHandler, testList);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).primitive(Boolean.TRUE);
    inOrder.verify(mockJsonContentHandler).endArray();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with a {@link Boolean} element and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test
  public void testHandleListWithBooleanElementAsFragment() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add(Boolean.TRUE);
    
    JsonContentUtil.handle(mockJsonContentHandler, testList, true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).primitive(Boolean.TRUE);
    inOrder.verify(mockJsonContentHandler).endArray();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with a {@link Number} element.
   */
  @Test
  public void testHandleListWithNumberElement() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add(Double.valueOf(3.14));
    
    JsonContentUtil.handle(mockJsonContentHandler, testList);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).primitive(Double.valueOf(3.14));
    inOrder.verify(mockJsonContentHandler).endArray();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with a {@link Number} element and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test
  public void testHandleListWithNumberElementAsFragment() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add(Double.valueOf(3.14));
    
    JsonContentUtil.handle(mockJsonContentHandler, testList, true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).primitive(Double.valueOf(3.14));
    inOrder.verify(mockJsonContentHandler).endArray();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with a {@link String} element.
   */
  @Test
  public void testHandleListWithStringElement() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add("Test String Value");
    
    JsonContentUtil.handle(mockJsonContentHandler, testList);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).primitive("Test String Value");
    inOrder.verify(mockJsonContentHandler).endArray();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with a {@link String} element and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test
  public void testHandleListWithStringElementAsFragment() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add("Test String Value");
    
    JsonContentUtil.handle(mockJsonContentHandler, testList, true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).primitive("Test String Value");
    inOrder.verify(mockJsonContentHandler).endArray();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with an unsupported object value element.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testHandleListWithUnsupportedObjectElement() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add(new Object());
    
    JsonContentUtil.handle(mockJsonContentHandler, testList);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with an unsupported object value element and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testHandleListWithUnsupportedObjectElementAsFragment() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add(new Object());
    
    JsonContentUtil.handle(mockJsonContentHandler, testList, true);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with a {@link Map} element.
   */
  @Test
  public void testHandleListWithMapElement() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add(Collections.emptyMap());
    
    JsonContentUtil.handle(mockJsonContentHandler, testList);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).endArray();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with a {@link Map} element and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test
  public void testHandleListWithMapElementAsFragment() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add(Collections.emptyMap());
    
    JsonContentUtil.handle(mockJsonContentHandler, testList, true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).endArray();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with a {@link List} element.
   */
  @Test
  public void testHandleListWithListElement() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add(Collections.emptyList());
    
    JsonContentUtil.handle(mockJsonContentHandler, testList);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler, times(2)).startArray();
    inOrder.verify(mockJsonContentHandler, times(2)).endArray();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, List)}
   * method with a list with a {@link List} element and <code>fragment<code>
   * set to <code>true</code>.
   */
  @Test
  public void testHandleListWithListElementAsFragment() {
    List<Object> testList = new LinkedList<Object>();
    
    testList.add(Collections.emptyList());
    
    JsonContentUtil.handle(mockJsonContentHandler, testList, true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler, times(2)).startArray();
    inOrder.verify(mockJsonContentHandler, times(2)).endArray();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Object)}
   * method.
   */
  @Test
  public void testHandleAll() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("nullMember", null);
    testMap.put("booleanMember", Boolean.TRUE);
    testMap.put("numberMember", Double.valueOf(3.14));
    testMap.put("stringMember", "Test String Value");
    
    Map<String, Object> objectMemberMap = new LinkedHashMap<String, Object>();
    
    objectMemberMap.put("nullMember", null);
    objectMemberMap.put("booleanMember", Boolean.TRUE);
    objectMemberMap.put("numberMember", Double.valueOf(3.14));
    objectMemberMap.put("stringMember", "Test String Value");
    objectMemberMap.put("objectMember", Collections.emptyMap());
    objectMemberMap.put("arrayMember", Collections.emptyList());

    testMap.put("objectMember", objectMemberMap);
    
    List<Object> arrayMemberList = new LinkedList<Object>();
    
    arrayMemberList.add(null);
    arrayMemberList.add(Boolean.TRUE);
    arrayMemberList.add(Double.valueOf(3.14));
    arrayMemberList.add("Test String Value");
    arrayMemberList.add(Collections.emptyMap());
    arrayMemberList.add(Collections.emptyList());
    
    testMap.put("arrayMember", arrayMemberList);
    
    JsonContentUtil.handle(mockJsonContentHandler, (Object)testMap);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startJson();
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("nullMember");
    inOrder.verify(mockJsonContentHandler).primitive(null);
    inOrder.verify(mockJsonContentHandler).memberName("booleanMember");
    inOrder.verify(mockJsonContentHandler).primitive(Boolean.TRUE);
    inOrder.verify(mockJsonContentHandler).memberName("numberMember");
    inOrder.verify(mockJsonContentHandler).primitive(Double.valueOf(3.14));
    inOrder.verify(mockJsonContentHandler).memberName("stringMember");
    inOrder.verify(mockJsonContentHandler).primitive("Test String Value");
    inOrder.verify(mockJsonContentHandler).memberName("objectMember");
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("nullMember");
    inOrder.verify(mockJsonContentHandler).primitive(null);
    inOrder.verify(mockJsonContentHandler).memberName("booleanMember");
    inOrder.verify(mockJsonContentHandler).primitive(Boolean.TRUE);
    inOrder.verify(mockJsonContentHandler).memberName("numberMember");
    inOrder.verify(mockJsonContentHandler).primitive(Double.valueOf(3.14));
    inOrder.verify(mockJsonContentHandler).memberName("stringMember");
    inOrder.verify(mockJsonContentHandler).primitive("Test String Value");
    inOrder.verify(mockJsonContentHandler).memberName("objectMember");
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).memberName("arrayMember");
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).endArray();
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).memberName("arrayMember");
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).primitive(null);
    inOrder.verify(mockJsonContentHandler).primitive(Boolean.TRUE);
    inOrder.verify(mockJsonContentHandler).primitive(Double.valueOf(3.14));
    inOrder.verify(mockJsonContentHandler).primitive("Test String Value");
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler, times(2)).endArray();
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).endJson();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
  
  /**
   * Test the {@link JsonContentUtil#handle(JsonContentHandler, Object)}
   * method with <code>fragment<code> set to <code>true</code>.
   */
  @Test
  public void testHandleAllAsFragment() {
    Map<String, Object> testMap = new LinkedHashMap<String, Object>();
    
    testMap.put("nullMember", null);
    testMap.put("booleanMember", Boolean.TRUE);
    testMap.put("numberMember", Double.valueOf(3.14));
    testMap.put("stringMember", "Test String Value");
    
    Map<String, Object> objectMemberMap = new LinkedHashMap<String, Object>();
    
    objectMemberMap.put("nullMember", null);
    objectMemberMap.put("booleanMember", Boolean.TRUE);
    objectMemberMap.put("numberMember", Double.valueOf(3.14));
    objectMemberMap.put("stringMember", "Test String Value");
    objectMemberMap.put("objectMember", Collections.emptyMap());
    objectMemberMap.put("arrayMember", Collections.emptyList());

    testMap.put("objectMember", objectMemberMap);
    
    List<Object> arrayMemberList = new LinkedList<Object>();
    
    arrayMemberList.add(null);
    arrayMemberList.add(Boolean.TRUE);
    arrayMemberList.add(Double.valueOf(3.14));
    arrayMemberList.add("Test String Value");
    arrayMemberList.add(Collections.emptyMap());
    arrayMemberList.add(Collections.emptyList());
    
    testMap.put("arrayMember", arrayMemberList);
    
    JsonContentUtil.handle(mockJsonContentHandler, (Object)testMap, true);
    
    InOrder inOrder = inOrder(mockJsonContentHandler);
    
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("nullMember");
    inOrder.verify(mockJsonContentHandler).primitive(null);
    inOrder.verify(mockJsonContentHandler).memberName("booleanMember");
    inOrder.verify(mockJsonContentHandler).primitive(Boolean.TRUE);
    inOrder.verify(mockJsonContentHandler).memberName("numberMember");
    inOrder.verify(mockJsonContentHandler).primitive(Double.valueOf(3.14));
    inOrder.verify(mockJsonContentHandler).memberName("stringMember");
    inOrder.verify(mockJsonContentHandler).primitive("Test String Value");
    inOrder.verify(mockJsonContentHandler).memberName("objectMember");
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).memberName("nullMember");
    inOrder.verify(mockJsonContentHandler).primitive(null);
    inOrder.verify(mockJsonContentHandler).memberName("booleanMember");
    inOrder.verify(mockJsonContentHandler).primitive(Boolean.TRUE);
    inOrder.verify(mockJsonContentHandler).memberName("numberMember");
    inOrder.verify(mockJsonContentHandler).primitive(Double.valueOf(3.14));
    inOrder.verify(mockJsonContentHandler).memberName("stringMember");
    inOrder.verify(mockJsonContentHandler).primitive("Test String Value");
    inOrder.verify(mockJsonContentHandler).memberName("objectMember");
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).memberName("arrayMember");
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).endArray();
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).memberName("arrayMember");
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler).primitive(null);
    inOrder.verify(mockJsonContentHandler).primitive(Boolean.TRUE);
    inOrder.verify(mockJsonContentHandler).primitive(Double.valueOf(3.14));
    inOrder.verify(mockJsonContentHandler).primitive("Test String Value");
    inOrder.verify(mockJsonContentHandler).startObject();
    inOrder.verify(mockJsonContentHandler).endObject();
    inOrder.verify(mockJsonContentHandler).startArray();
    inOrder.verify(mockJsonContentHandler, times(2)).endArray();
    inOrder.verify(mockJsonContentHandler).endObject();
    
    verifyNoMoreInteractions(mockJsonContentHandler);
  }
}
