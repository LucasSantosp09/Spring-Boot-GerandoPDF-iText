package Lucas.controllers;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Template;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.IOException;
import com.itextpdf.io.source.ByteArrayOutputStream;

import Lucas.models.Curso;




@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	
	private TemplateEngine templateEngine;

	@GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public byte[] gerarPdfSimples () {
		
		ByteArrayOutputStream pdfSteam = new ByteArrayOutputStream();
		HtmlConverter.convertToPdf("<h1 style ='color: red'>Olá, mundo ! </h1>",pdfSteam );
		return pdfSteam.toByteArray();
	}
	
	
	@GetMapping(value = "/relatorio", produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public byte[] gerarPdfArquivoHTML () throws java.io.IOException{
		ByteArrayOutputStream PdfStream = new ByteArrayOutputStream();
		//Resource htmlStream = resourceLoader.getResource("classpath:relatorio.html");
		Resource htmlStream = resourceLoader.getResource("classpath:relatorio.html");
		HtmlConverter.convertToPdf(htmlStream.getInputStream(), PdfStream); 
		return PdfStream.toByteArray();
							
	}
	
	@GetMapping(value = "/cursos", produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public byte[] gerarPdfTemplateThymeleaf() {
		List<Curso> curso = new ArrayList<>();
		curso.add(new Curso("Spring Framework - Fundamentos", "Cleysson Lima", 10));
		curso.add(new Curso("Spring Framework - Template Thymeleaf", "Cleysson Lima", 10));
		curso.add(new Curso("Spring Framework - Data JPA", "Cleysson Lima", 10));
		
		Context context = new Context();
		context.setVariable("cursos", curso);
		
		String html  =  templateEngine.process("cursos.html", context);
		
		ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
		HtmlConverter.convertToPdf(html, pdfStream);
		
		return pdfStream.toByteArray();
	}
}
