package Lucas.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;



@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public byte[] gerarPdfSimples () {
		
		ByteArrayOutputStream pdfSteam = new ByteArrayOutputStream();
		HtmlConverter.convertToPdf("<h1 style ='color: red'>Olá, mundo ! </h1>",pdfSteam );
		return pdfSteam.toByteArray();
	}
}