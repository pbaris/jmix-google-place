package gr.netmechanics.jmix.gplace.kit;

import static io.jmix.flowui.kit.meta.StudioProperty.Category.DATA_BINDING;
import static io.jmix.flowui.kit.meta.StudioProperty.Category.GENERAL;
import static io.jmix.flowui.kit.meta.StudioProperty.Category.LOOK_AND_FEEL;
import static io.jmix.flowui.kit.meta.StudioProperty.Category.POSITION;
import static io.jmix.flowui.kit.meta.StudioProperty.Category.SIZE;
import static io.jmix.flowui.kit.meta.StudioProperty.Category.VALIDATION;

import gr.netmechanics.jmix.gplace.component.GooglePlacePicker;
import io.jmix.flowui.kit.meta.StudioComponent;
import io.jmix.flowui.kit.meta.StudioPropertiesBinding;
import io.jmix.flowui.kit.meta.StudioProperty;
import io.jmix.flowui.kit.meta.StudioPropertyType;
import io.jmix.flowui.kit.meta.StudioUiKit;

/**
 * @author Panos Bariamis (pbaris)
 */
@StudioUiKit
public interface StudioComponents {

    @StudioComponent(
        name = "GooglePlacePicker",
        classFqn = "gr.netmechanics.jmix.gplace.component.GooglePlacePicker",
        category = "Components",
        xmlElement = "googlePlacePicker",
        xmlns = "http://schemas.netmechanics.gr/jmix/ui",
        xmlnsAlias = "nm",
        icon = "gr/netmechanics/jmix/gplace/kit/gplace_picker.svg",
        properties = {
            @StudioProperty(xmlAttribute = "alignSelf", category = POSITION, type = StudioPropertyType.ENUMERATION,
                classFqn = "com.vaadin.flow.component.orderedlayout.FlexComponent$Alignment",
                defaultValue = "AUTO",
                options = {"START", "END", "CENTER", "STRETCH", "BASELINE", "AUTO"}),
            @StudioProperty(xmlAttribute = "apiKey", category = GENERAL, type = StudioPropertyType.STRING),
            @StudioProperty(xmlAttribute = "languageCode", category = GENERAL, type = StudioPropertyType.STRING),
            @StudioProperty(xmlAttribute = "autofocus", type = StudioPropertyType.BOOLEAN, defaultValue = "false"),
            @StudioProperty(xmlAttribute = "ariaLabel", type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "ariaLabelledBy", type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "classNames", category = LOOK_AND_FEEL, type = StudioPropertyType.VALUES_LIST),
            @StudioProperty(xmlAttribute = "css", category = LOOK_AND_FEEL, type = StudioPropertyType.STRING),
            @StudioProperty(xmlAttribute = "overlayClass", category = LOOK_AND_FEEL, type = StudioPropertyType.STRING),
            @StudioProperty(xmlAttribute = "overlayWidth", category = LOOK_AND_FEEL, type = StudioPropertyType.SIZE),
            @StudioProperty(xmlAttribute = "clearButtonVisible", category = LOOK_AND_FEEL, type = StudioPropertyType.BOOLEAN, defaultValue = "false"),
            @StudioProperty(xmlAttribute = "colspan", category = POSITION, type = StudioPropertyType.INTEGER),
            @StudioProperty(xmlAttribute = "dataContainer", category = DATA_BINDING,
                type = StudioPropertyType.COLLECTION_OR_INSTANCE_DATA_CONTAINER_REF),
            @StudioProperty(xmlAttribute = "enabled", category = GENERAL, type = StudioPropertyType.BOOLEAN, defaultValue = "true"),
            @StudioProperty(xmlAttribute = "errorMessage", category = VALIDATION, type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "height", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "helperText", category = GENERAL, type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "id", category = GENERAL, type = StudioPropertyType.COMPONENT_ID),
            @StudioProperty(xmlAttribute = "label", category = GENERAL, type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "maxHeight", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "maxWidth", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "minHeight", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "minWidth", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"}),
            @StudioProperty(xmlAttribute = "placeholder", category = GENERAL, type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "property", category = DATA_BINDING, type = StudioPropertyType.PROPERTY_REF, typeParameter = "V"),
            @StudioProperty(xmlAttribute = "readOnly", category = GENERAL, type = StudioPropertyType.BOOLEAN, defaultValue = "false"),
            @StudioProperty(xmlAttribute = "required", category = VALIDATION, type = StudioPropertyType.BOOLEAN, defaultValue = "false"),
            @StudioProperty(xmlAttribute = "requiredMessage", category = VALIDATION, type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "tabIndex", type = StudioPropertyType.INTEGER),
            @StudioProperty(xmlAttribute = "focusShortcut", type = StudioPropertyType.SHORTCUT_COMBINATION),
            @StudioProperty(xmlAttribute = "themeNames", category = LOOK_AND_FEEL, type = StudioPropertyType.VALUES_LIST,
                options = {"small", "align-left", "align-center", "align-right", "helper-above-field", "always-float-label"}),
            @StudioProperty(xmlAttribute = "title", type = StudioPropertyType.LOCALIZED_STRING),
            @StudioProperty(xmlAttribute = "visible", category = GENERAL, type = StudioPropertyType.BOOLEAN, defaultValue = "true"),
            @StudioProperty(xmlAttribute = "width", category = SIZE, type = StudioPropertyType.SIZE, options = {"AUTO", "100%"})
        },
        propertiesBindings = {
            @StudioPropertiesBinding(source = "dataContainer", item = "property")
        }
    )
    GooglePlacePicker googlePlacePicker();
}
