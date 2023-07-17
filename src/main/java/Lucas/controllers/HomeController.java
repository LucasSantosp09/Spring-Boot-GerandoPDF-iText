package Lucas.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.IOException;
import com.itextpdf.io.source.ByteArrayOutputStream;



@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private ResourceLoader resourceLoader;

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
}
