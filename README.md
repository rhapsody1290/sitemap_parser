# sitemap_parser

1、百度sitemap.xml转化为url列表，存放在txt文件中
2、生成可执行的jar文件，需要生成MANIFEST.MF文件

	<build>
	    <plugins>
	
	        <plugin>
	            <groupId>org.apache.maven.plugins</groupId>
	            <artifactId>maven-jar-plugin</artifactId>
	            <version>2.6</version>
	            <configuration>
	                <archive>
	                    <manifest>
	                        <addClasspath>true</addClasspath>
	                        <classpathPrefix>lib/</classpathPrefix>
	                        <mainClass>APP</mainClass>
	                    </manifest>
	                </archive>
	            </configuration>
	        </plugin>
	        <plugin>
	            <groupId>org.apache.maven.plugins</groupId>
	            <artifactId>maven-dependency-plugin</artifactId>
	            <version>2.10</version>
	            <executions>
	                <execution>
	                    <id>copy-dependencies</id>
	                    <phase>package</phase>
	                    <goals>
	                        <goal>copy-dependencies</goal>
	                    </goals>
	                    <configuration>
	                        <outputDirectory>${project.build.directory}/lib</outputDirectory>
	                    </configuration>
	                </execution>
	            </executions>
	        </plugin>
	
	    </plugins>
	</build>

3、在target为打包结果，需要解析的baidusitemap.xml文件与sitemap_parser-1.0-SNAPSHOT.jar位于同一目录

执行命令行，输出结果

	D:\IDEA\workspace\sitemap_parser\target>java -jar sitemap_parser-1.0-SNAPSHOT.jar