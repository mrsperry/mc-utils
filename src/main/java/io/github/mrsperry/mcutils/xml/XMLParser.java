package io.github.mrsperry.mcutils.xml;

import javafx.util.Pair;

import org.bukkit.Bukkit;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffectType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XMLParser {
    private XMLObject root;

    public XMLParser(File file) {
        Document document;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        } catch (Exception ex) {
            Bukkit.getLogger().severe("Could not create document instance: " + file.getName());
            return;
        }

        Node root = document.getDocumentElement();
        root.normalize();

        this.root = new XMLObject((Element) root, null);
    }

    // * * * * * * * *
    // Content
    // * * * * * * * *

    public byte getContentByte(XMLObject parent, byte value) {
        try {
            return Byte.parseByte(parent.getContent());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse byte from content: " + parent.getName() + " : " + parent.getContent());
            return value;
        }
    }

    public int getContentInt(XMLObject parent, int value) {
        try {
            return Integer.parseInt(parent.getContent());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse int from content: " + parent.getName() + " : " + parent.getContent());
            return value;
        }
    }

    public double getContentDouble(XMLObject parent, double value) {
        try {
            return Double.parseDouble(parent.getContent());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse double from content: " + parent.getName() + " : " + parent.getContent());
            return value;
        }
    }

    public boolean getContentboolean(XMLObject parent, boolean value) {
        try {
            return Boolean.parseBoolean(parent.getContent());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse boolean from content: " + parent.getName() + " : " + parent.getContent());
            return value;
        }
    }

    public Pair<Integer, Integer> getContentRange(XMLObject parent, Pair<Integer, Integer> value) {
        try {
            String[] range = parent.getContent().split("-");
            if (range.length == 2) {
                return new Pair<>(Integer.parseInt(range[0]), Integer.parseInt(range[1]));
            } else if(range.length == 1) {
                return new Pair<>(Integer.parseInt(range[0]), Integer.parseInt(range[0]));
            }

            throw new Exception("Range must be two numbers separated by a dash");
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse range from content: " + parent.getName() + " : " + parent.getContent());
            return value;
        }
    }

    public Material getContentMaterial(XMLObject parent, Material value) {
        try {
            return Material.valueOf(parent.getContent().replace(" ", "_").toUpperCase());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse material from content: " + parent.getName() + " : " + parent.getContent());
            return value;
        }
    }

    public PotionEffectType getContentPotionEffect(XMLObject parent, PotionEffectType value) {
        try {
            PotionEffectType type = PotionEffectType.getByName(parent.getContent().replace(" ", "_").toUpperCase());
            if (type == null) {
                throw new Exception("Potion type cannot be null");
            }
            return type;
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse potion effect from content: " + parent.getName() + " : " + parent.getContent());
            return value;
        }
    }

    public EntityType getContentEntityType(XMLObject parent, EntityType value) {
        try {
            return EntityType.valueOf(parent.getContent().replace(" ", "_").toUpperCase());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse entity type from content: " + parent.getName() + " : " + parent.getContent());
            return value;
        }
    }

    public Color getContentColor(XMLObject parent, Color value) {
        try {
            String[] split = parent.getContent().split(",");
            if (split.length == 1) {
                return this.parseColor(split[0]);
            }
            return Color.fromRGB(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse color from content: " + parent.getName() + " : " + parent.getContent());
            return value;
        }
    }

    // * * * * * * * *
    // Attributes
    // * * * * * * * *

    public String getAttributeString(XMLObject parent, String name, String value) {
        return this.getAttribute(parent, name, value).toString();
    }

    public byte getAttributeByte(XMLObject parent, String name, byte value) {
        try {
            return Byte.parseByte(this.getAttribute(parent, name, value).toString());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse byte from attribute: " + parent.getName() + " : " + name);
            return value;
        }
    }

    public int getAttributeInt(XMLObject parent, String name, int value) {
        try {
            return Integer.parseInt(this.getAttribute(parent, name, value).toString());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse int from attribute: " + parent.getName() + " : " + name);
            return value;
        }
    }

    public double getAttributeDouble(XMLObject parent, String name, double value) {
        try {
            return Double.parseDouble(this.getAttribute(parent, name, value).toString());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse double from attribute: " + parent.getName() + " : " + name);
            return value;
        }
    }

    public boolean getAttributeBoolean(XMLObject parent, String name, boolean value) {
        try {
            return Boolean.parseBoolean(this.getAttribute(parent, name, value).toString());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse boolean from attribute: " + parent.getName() + " : " + name);
            return value;
        }
    }

    public Pair<Integer, Integer> getAttributeRange(XMLObject parent, String name, Pair<Integer, Integer> value) {
        try {
            String[] range = this.getAttribute(parent, name, value).toString().split("-");
            if (range.length == 2) {
                return new Pair<>(Integer.parseInt(range[0]), Integer.parseInt(range[1]));
            } else if(range.length == 1) {
                return new Pair<>(Integer.parseInt(range[0]), Integer.parseInt(range[0]));
            }

            throw new Exception("Range must be two numbers separated by a dash");
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse range from attribute: " + parent.getName() + " : " + name);
            return value;
        }
    }

    public Material getAttributeMaterial(XMLObject parent, String name, Material value) {
        try {
            return Material.valueOf(this.getAttribute(parent, name, value).toString().replace(" ", "_").toUpperCase());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse material from attribute: " + parent.getName() + " : " + name);
            return value;
        }
    }

    public PotionEffectType getAttributePotionEffect(XMLObject parent, String name, PotionEffectType value) {
        try {
            PotionEffectType type = PotionEffectType.getByName(parent.getContent().replace(" ", "_").toUpperCase());
            if (type == null) {
                throw new Exception("Potion type cannot be null");
            }
            return type;
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse potion effect from attribute: " + parent.getName() + " : " + name);
            return value;
        }
    }

    public EntityType getAttributeEntityType(XMLObject parent, String name, EntityType value) {
        try {
            return EntityType.valueOf(this.getAttribute(parent, name, value).toString().replace(" ", "_").toUpperCase());
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse entity type from attribute: " + parent.getName() + " : " + name);
            return value;
        }
    }

    public Color getAttributeColor(XMLObject parent, String name, Color value) {
        try {
            String[] split = this.getAttribute(parent, name, value).toString().split(",");
            if (split.length == 1) {
                return this.parseColor(split[0]);
            }
            return Color.fromRGB(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        } catch (Exception ex) {
            Bukkit.getLogger().warning("Could not parse color from attribute: " + parent.getName() + " : " + name);
            return value;
        }
    }

    // * * * * * * * *
    // Object finding
    // * * * * * * * *

    public XMLObject getRoot() {
        return this.root;
    }

//    public XMLObject findObject(String path) {
//        return this.find(this.root, path, 0);
//    }
//
//    public XMLObject findObject(String path, XMLObject start) {
//        return this.find(start, path, 0);
//    }
//
//    public List<XMLObject> findAllObjects(String path) {
//        XMLObject object = this.find(this.root, path, 0);
//        return (object == null ? new ArrayList<>() : object.getChildren());
//    }
//
//    public List<XMLObject> findAllObjects(String path, XMLObject start) {
//        XMLObject object = this.find(start, path, 0);
//        return (object == null ? new ArrayList<>() : object.getChildren());
//    }
//
//    private XMLObject find(XMLObject object, String path, int index) {
//        String[] split = path.split("\\.");
//
//        for (XMLObject child : object.getChildren()) {
//            if (child.getName().equals(split[split.length - 1])) {
//                return child;
//            }
//
//            if (child.getName().equals(split[index])) {
//                return find(child, path, ++index);
//            }
//        }
//
//        Bukkit.getLogger().warning("Could not find XML object with name: " + path);
//        return null;
//    }

    // * * * * * * * *
    // Private methods
    // * * * * * * * *

    private Color parseColor(String color) throws Exception {
        switch (color.toLowerCase()) {
            case "aqua":
                return Color.AQUA;
            case "black":
                return Color.BLACK;
            case "blue":
                return Color.BLUE;
            case "fuchsia":
                return Color.FUCHSIA;
            case "gray":
                return Color.GRAY;
            case "green":
                return Color.GREEN;
            case "lime":
                return Color.LIME;
            case "maroon":
                return Color.MAROON;
            case "navy":
                return Color.NAVY;
            case "olive":
                return Color.OLIVE;
            case "orange":
                return Color.ORANGE;
            case "purple":
                return Color.PURPLE;
            case "red":
                return Color.RED;
            case "silver":
                return Color.SILVER;
            case "teal":
                return Color.TEAL;
            case "white":
                return Color.WHITE;
            case "yellow":
                return Color.YELLOW;
            default:
                throw new Exception("Invalid color: " + color);
        }
    }

    private Object getAttribute(XMLObject parent, String name, Object value) {
        HashMap<String, String> attributes = parent.getAttributes();
        if (attributes.containsKey(name)) {
            return attributes.get(name);
        }

        return value;
    }
}
