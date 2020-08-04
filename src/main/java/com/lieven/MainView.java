package com.lieven;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;

public class MainView extends VerticalLayout {

	@Autowired
	DocumentService service;

	private String category;

	public void onCategoryParameterChange(String category) {
		this.category = category;
		init();
	}

	public MainView() {
	}

	private void init() {

		Label catLabel = new Label(category);

		File file = service.getDoc(category);

		Anchor aPrint = new Anchor();
		aPrint.setText("Download Label PDF");
		aPrint.setHref(createLabelStreamResource(file));
		aPrint.getElement().setAttribute("download", true);

		add(catLabel, aPrint);
	}

	private StreamResource createLabelStreamResource(File file) {
		StreamResource streamResource = new StreamResource("label.pdf", () -> {
			try {
				return new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return new ByteArrayInputStream("Something went wrong".getBytes());
		});
		streamResource.setContentType("application/pdf");
		return streamResource;
	}
}
