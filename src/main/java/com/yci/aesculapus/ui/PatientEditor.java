package com.yci.aesculapus.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.yci.aesculapus.patient.Patient;
import com.yci.aesculapus.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class PatientEditor extends VerticalLayout implements KeyNotifier {

    private final PatientRepository repository;

    private Patient patient;

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Patient> binder = new Binder<>(Patient.class);
    private ChangeHandler changeHandler;

    @Autowired
    public PatientEditor(PatientRepository repository) {
        this.repository = repository;

        add(firstName, lastName, actions);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editPatient(patient));
        setVisible(false);
    }

    void delete() {
        repository.delete(patient);
        changeHandler.onChange();
    }

    void save() {

        repository.save(patient);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editPatient(Patient c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            patient = repository.findById(c.getId()).get();
        } else {
            patient = c;
        }

        cancel.setVisible(persisted);
        binder.setBean(patient);
        setVisible(true);
        firstName.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }
}
