import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Created by mingqian on 2017/2/28.
 */
public class APP {
    public static void main(String[] args) throws ParserConfigurationException, IOException {
        //创建解析器工厂实例，并生成解析器
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //创建需要解析的文档对象
        String path = "baidusitemap.xml";
        File f = new File(path);
        //解析文档，并返回一个Document对象，此时xml文档已加载到内存中
        //好吧，让解析来得更猛烈些吧，其余的事就是获取数据了
        Document doc = null;
        try {
            doc = builder.parse(f);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("未找到baidusitemap.xml文件，该文件需要与sitemap_parser.jar位于同一目录下");
            return;
        }

        //获取文档根元素
        //你问我为什么这么做?因为文档对象本身就是树形结构，这里就是树根
        //当然，你也可以直接找到元素集合，省略此步骤
        Element root = doc.getDocumentElement();

        //上面找到了根节点，这里开始获取根节点下的元素集合
        NodeList list = root.getElementsByTagName("url");

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.getLength(); i++) {
            //通过item()方法找到集合中的节点，并向下转型为Element对象
            Element n = (Element) list.item(i);
            String url = n.getElementsByTagName("loc").item(0).getFirstChild().getNodeValue();
            //System.out.println(i+1 + " " + url);
            if (i != list.getLength() - 1) {
                stringBuilder.append(url).append("\n");
            }
            //获取对象中的属性map，用for循环提取并打印
            /*NamedNodeMap node = n.getAttributes();
            for (int x = 0; x < node.getLength(); x++) {
                Node nn = node.item(x);
                System.out.println(nn.getNodeName() + ": " + nn.getNodeValue());
            }
            //打印元素内容，代码很纠结，差不多是个固定格式
            System.out.println("title: " +n.getElementsByTagName("title").item(0).getFirstChild().getNodeValue());
            System.out.println("author: " + n.getElementsByTagName("author").item(0).getFirstChild().getNodeValue());
            System.out.println();*/
        }

        OutputStream outputStream = new FileOutputStream("result.txt");
        byte[] bytes = stringBuilder.toString().getBytes("utf-8");
        outputStream.write(bytes, 0, bytes.length);
        if (outputStream != null) {
            outputStream.close();
        }
        System.out.println("输出结果完毕，查看result.txt文件");
    }
}
