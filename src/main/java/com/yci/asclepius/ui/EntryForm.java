package com.yci.asclepius.ui;

import com.vaadin.data.Binder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.extern.slf4j.Slf4j;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Set;

@Slf4j
public  class EntryForm<T> extends Composite<FormLayout> {

    private Class<T> clz;

    private Class<T> getGenericName() {
        return (Class<T>)((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }


    public EntryForm() {

        this.clz = getGenericName();

        Label infoLabel = new Label();
        FormLayout layoutWithBinder = new FormLayout();
        Binder<T> binder = new Binder<>();

        // The object that will be edited

        try {
            T item = clz.newInstance();
            final Set<Field> allFields = ReflectionUtils.getAllFields(clz, p -> true);
            VerticalLayout formLayout = new VerticalLayout();
            for (Field field : allFields) {
                Class fieldClass = field.getType();
                String name = field.getName();
                Component control =  null;
                if (fieldClass.equals(String.class)) {
                    control = new TextField();
                } else if (fieldClass.isAssignableFrom(Number.class)) {
                    control = new TextField();
                    ((TextField)control).setPattern("^(0|[1-9][0-9]*|[1-9][0-9]{0,2}(,[0-9]{3,3})*)$");
                   // getContent().addFormItem(control, name);//getTranslation("patient.form."+name));
                } else if (fieldClass.equals(Boolean.class)) {
                    control = new Checkbox();
                   // getContent().addFormItem(control, name);//getTranslation("patient.form."+name));
                } else if (fieldClass.isAssignableFrom(Collection.class)) {
                    continue;
                } else if (fieldClass.equals(LocalDate.class)) {
                    control = new DatePicker();
                   // getContent().addFormItem(control, name);//getTranslation("patient.form."+name));
                } else if (fieldClass.equals(ZonedDateTime.class)) {
                    control = new DatePicker();
                    getContent().addFormItem(control, name);//getTranslation("patient.form."+name));
                }

                if (control != null) {
                    Label label = new Label(name);
                    label.getStyle().set("margin-left", "auto");
                    ((HasStyle) control).getStyle().set("margin-right", "auto");
                    HorizontalLayout controlPair = new HorizontalLayout();
                    controlPair.add(label, control);
                    //controlPair.getStyle().set("margin-left", "auto");
                    formLayout.add(controlPair);
                }

            }
            getContent().add(formLayout);
            Button save = new Button("Save", VaadinIcon.CHECK.create());
            Button reset = new Button("Reset",VaadinIcon.CLOSE.create());
            HorizontalLayout actions = new HorizontalLayout();
            actions.add(save, reset);
            save.getStyle().set("marginRight", "10px");

            getContent().add(actions);

            save.addClickListener(event -> {
            if (binder.writeBeanIfValid(item)) {
               log.info("Saved");
            } else {
                /*BinderValidationStatus<Contact> validate = binder.validate();
                String errorText = validate.getFieldValidationStatuses()
                        .stream().filter(BindingValidationStatus::isError)
                        .map(BindingValidationStatus::getMessage)
                        .map(Optional::get).distinct()
                        .collect(Collectors.joining(", "));
                infoLabel.setText("There are errors: " + errorText);*/
                log.info("Error");
            }
        });

        reset.addClickListener(event -> {
            // clear fields by setting null
            binder.readBean(null);
            infoLabel.setText("");
        });

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Create the fields
        /*TextField firstName = new TextField();
        firstName.setValueChangeMode(ValueChangeMode.EAGER);
        TextField lastName = new TextField();
        lastName.setValueChangeMode(ValueChangeMode.EAGER);
        TextField phone = new TextField();
        phone.setValueChangeMode(ValueChangeMode.EAGER);
        TextField email = new TextField();
        email.setValueChangeMode(ValueChangeMode.EAGER);
        DatePicker birthDate = new DatePicker();
        Checkbox doNotCall = new Checkbox("Do not call");
        Label infoLabel = new Label();
        NativeButton save = new NativeButton("Save");
        NativeButton reset = new NativeButton("Reset");

        layoutWithBinder.addFormItem(firstName, "First name");
        layoutWithBinder.addFormItem(lastName, "Last name");
        layoutWithBinder.addFormItem(birthDate, "Birthdate");
        layoutWithBinder.addFormItem(email, "E-mail");
        FormLayout.FormItem phoneItem = layoutWithBinder.addFormItem(phone, "Phone");
        phoneItem.add(doNotCall);

        // Button bar
        HorizontalLayout actions = new HorizontalLayout();
        actions.add(save, reset);
        save.getStyle().set("marginRight", "10px");

        SerializablePredicate<String> phoneOrEmailPredicate = value -> !phone
                .getValue().trim().isEmpty()
                || !email.getValue().trim().isEmpty();*/

        // E-mail and phone have specific validators
        /*Binder.Binding<T, String> emailBinding = binder.forField(email)
                .withValidator(phoneOrEmailPredicate,
                        "Both phone and email cannot be empty")
                .withValidator(new EmailValidator("Incorrect email address"))
                .bind(Contact::getEmail, Contact::setEmail);

        Binding<Contact, String> phoneBinding = binder.forField(phone)
                .withValidator(phoneOrEmailPredicate,
                        "Both phone and email cannot be empty")
                .bind(Contact::getPhone, Contact::setPhone);*/

// Trigger cross-field validation when the other field is changed
       // email.addValueChangeListener(event -> phoneBinding.validate());
       // phone.addValueChangeListener(event -> emailBinding.validate());

// First name and last name are required fields
        /*firstName.setRequiredIndicatorVisible(true);
        lastName.setRequiredIndicatorVisible(true);

        binder.forField(firstName)
                .withValidator(new StringLengthValidator(
                        "Please add the first name", 1, null))
                .bind(Contact::getFirstName, Contact::setFirstName);
        binder.forField(lastName)
                .withValidator(new StringLengthValidator(
                        "Please add the last name", 1, null))
                .bind(Contact::getLastName, Contact::setLastName);*/

// Birthdate and doNotCall don't need any special validators
       // binder.bind(doNotCall, Contact::isDoNotCall, Contact::setDoNotCall);
       // binder.bind(birthDate, Contact::getBirthDate, Contact::setBirthDate);

// Click listeners for the buttons

        /*save.addClickListener(event -> {
            if (binder.writeBeanIfValid(item)) {
                infoLabel.setText("Saved bean values: " + item);
            } else {
                BinderValidationStatus<Contact> validate = binder.validate();
                String errorText = validate.getFieldValidationStatuses()
                        .stream().filter(BindingValidationStatus::isError)
                        .map(BindingValidationStatus::getMessage)
                        .map(Optional::get).distinct()
                        .collect(Collectors.joining(", "));
                infoLabel.setText("There are errors: " + errorText);
            }
        });*/

        /*reset.addClickListener(event -> {
            // clear fields by setting null
            binder.readBean(null);
            infoLabel.setText("");
            doNotCall.setValue(false);
        });*/

    }
}
