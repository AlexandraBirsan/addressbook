package com.addressbook.service.contacts;

import com.addressbook.model.ContactDto;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by birsan on 5/5/2016.
 */
@ManagedBean(name = "export")
@ApplicationScoped
public class ExportBean {
    // TODO export also profile photo
    public void download(List<ContactDto> contactDtos) throws IOException, JSONException {
        String path = export(contactDtos);
        File file = new File(path);
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setHeader("Content-Disposition", "attachement;filename=file.json");
        response.setContentLength((int) file.length());
        try (ServletOutputStream out = response.getOutputStream()) {
            FileInputStream is = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                out.write(buffer);
                out.flush();
            }
            FacesContext.getCurrentInstance().getResponseComplete();
        }
    }

    private String export(List<ContactDto> contactDtos) throws JSONException, IOException {
        JSONObject allContacts = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (ContactDto contactDto : contactDtos) {
            JSONObject singleContact = new JSONObject();
            singleContact.put("First Name", contactDto.getFirstName());
            singleContact.put("Last Name", contactDto.getLastName());
            singleContact.put("Company", contactDto.getCompany());
            singleContact.put("Phone Numbers", contactDto.getPhoneNumber());
            allContacts.put("Contact", singleContact);
            jsonArray.put(singleContact);
        }
        File temp = File.createTempFile("export", ".json");
        String absolutePath = temp.getAbsolutePath();
        try (FileWriter fileWriter = new FileWriter(absolutePath)) {
            fileWriter.write(jsonArray.toString());
        }
        return absolutePath;
    }


}
