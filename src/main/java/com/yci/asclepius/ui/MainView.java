package com.yci.asclepius.ui;

import com.google.common.base.Joiner;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.yci.asclepius.patient.Patient;
import com.yci.asclepius.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route
@Slf4j
public class MainView extends VerticalLayout {

    private PatientRepository patientRepository;

    private final PatientEditor editor;

    final Grid<Patient> grid;

    final TextField filter;

    private final Button addNewBtn;

    public MainView(PatientRepository repo, PatientEditor editor) {


        Tab tab1 = new Tab("Tab one");
        Tab tab2 = new Tab("Tab two");
        Tab tab3 = new Tab("Tab three");
        Tabs tabs = new Tabs(tab1, tab2, tab3);

        HorizontalLayout layout = new HorizontalLayout(tabs);
        add(layout);

        this.patientRepository = repo;
        this.editor = editor;

        this.grid = new Grid<>(Patient.class);
        this.filter = new TextField();
        this.addNewBtn = new Button(getTranslation("patient.new"), VaadinIcon.PLUS.create());
        Button deleteButton = new Button("Delete Patient", VaadinIcon.MINUS.create());
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn, deleteButton);
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

        //addNewBtn.addClickListener(e -> editor.editPatient(new Patient()));

        addNewBtn.addClickListener(e -> {

            PatientEntryForm patientEntryForm = new PatientEntryForm();
            patientEntryForm.setVisible(true);

            Dialog dialog = new Dialog();
            Stream<Component> children = patientEntryForm.getChildren();
            List<Component> collect = children.collect(Collectors.toList());
            log.info(Joiner.on(",").join(collect));
            dialog.add(collect.toArray(new Component[]{}));
            dialog.open();

        });

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
