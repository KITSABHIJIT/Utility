package com.build.rest.webservice;

import java.io.File;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.build.rest.util.CommonUtility;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/file")
public class FileService {
	
	private static final String FILE_PATH = System.getProperty("user.home")+System.getProperty("file.separator")+"restWebservice";

	@GET
	@Path("/getText/{name}")
	@Produces("text/plain")
	public Response getTextFile(@PathParam("name") String fileName) {

		File file = new File(FILE_PATH+System.getProperty("file.separator")+fileName);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=\"file_from_server.log\"");
		return response.build();

	}
	
	@GET
	@Path("/getImage/{name}")
	@Produces("image/png")
	public Response getImageFile(@PathParam("name") String fileName) {

		File file = new File(FILE_PATH+System.getProperty("file.separator")+fileName);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=image_from_server.png");
		return response.build();

	}
	
	@GET
	@Path("/getPdf/{name}")
	@Produces("application/pdf")
	public Response getPdfFile(@PathParam("name") String fileName) {

		File file = new File(FILE_PATH+System.getProperty("file.separator")+fileName);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
				"attachment; filename=new-android-book.pdf");
		return response.build();

	}
	
	@GET
	@Path("/getExcel/{name}")
	@Produces("application/vnd.ms-excel")
	public Response getExcelFile(@PathParam("name") String fileName) {

		File file = new File(FILE_PATH+System.getProperty("file.separator")+fileName);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=new-excel-file.xls");
		return response.build();

	}
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String uploadedFileLocation = FILE_PATH+System.getProperty("file.separator")+System.getProperty("file.separator")+ fileDetail.getFileName();

		// save it
		CommonUtility.writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).entity(output).build();

	}
}
