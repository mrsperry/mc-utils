package io.github.mrsperry.mcutils.xml;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XMLObject {
    private String name = "";
    private String content = "";
    private HashMap<String, String> attributes = new HashMap<>();

    private XMLObject parent = null;
    private List<XMLObject> children = new ArrayList<>();

    public XMLObject(Element node, XMLObject parent) {
        this.name = node.getTagName();
        this.content = node.getTextContent();
        if (node.hasAttributes()) {
            NamedNodeMap map = node.getAttributes();
            for (int index = 0; index < map.getLength(); index++) {
                Node item = map.item(index);
                this.attributes.put(item.getNodeName(), item.getTextContent());
            }
        }

        this.parent = parent;
        if (node.hasChildNodes()) {
            NodeList childList = node.getChildNodes();
            for (int index = 0; index < childList.getLength(); index++) {
                if (childList.item(index).getNodeType() == Node.ELEMENT_NODE) {
                    this.children.add(new XMLObject((Element) childList.item(index), this));
                }
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public String getContent() {
        return this.content;
    }

    public HashMap<String, String> getAttributes() {
        return this.attributes;
    }

    public XMLObject getParent() {
        return this.parent;
    }

    public List<XMLObject> getChildren() {
        return this.children;
    }

    public XMLObject getFirstChild(String name) {
        for(int i = 0; i < this.children.size(); i++) {
            if(this.children.get(i).getName().equalsIgnoreCase(name)) {
                return this.children.get(i);
            }
        }
        return null;
    }

    public List<XMLObject> getAllChildren(String name) {
        List<XMLObject> result = new ArrayList<>();

        for(int i = 0; i < this.children.size(); i++) {
            if(this.children.get(i).getName().equalsIgnoreCase(name)) {
                result.add(this.children.get(i));
            }
        }
        return result;
    }
}
