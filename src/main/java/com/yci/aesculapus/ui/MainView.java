package com.yci.aesculapus.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.yci.aesculapus.patient.Patient;
import com.yci.aesculapus.repository.PatientRepository;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Route
public class MainView extends VerticalLayout {

    private PatientRepository patientRepository;

    private final PatientEditor editor;

    final Grid<Patient> grid;

    final TextField filter;

    private final Button addNewBtn;

    public MainView(PatientRepository repo, PatientEditor editor) {
        this.patientRepository = repo;
        this.editor = editor;
        this.grid = new Grid<>(Patient.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New Patient", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("200px");
        grid.setColumns("id", "firstName", "lastName");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last name");

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listPatients(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editPatient(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editPatient(new Patient()));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listPatients(filter.getValue());
        });

        listPatients(null);
    }

    void listPatients(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            Iterable<Patient> all = patientRepository.findAll();
            grid.setItems(Lists.newArrayList(all));
        } else {
            grid.setItems(patientRepository.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }
}
