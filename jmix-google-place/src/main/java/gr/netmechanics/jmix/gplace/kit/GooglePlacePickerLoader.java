package gr.netmechanics.jmix.gplace.kit;

import gr.netmechanics.jmix.gplace.component.GooglePlacePicker;
import io.jmix.flowui.xml.layout.loader.component.AbstractComboBoxLoader;
import io.jmix.flowui.xml.layout.support.DataLoaderSupport;

/**
 * @author Panos Bariamis (pbaris)
 */
public class GooglePlacePickerLoader extends AbstractComboBoxLoader<GooglePlacePicker> {
    protected DataLoaderSupport dataLoaderSupport;

    @Override
    protected GooglePlacePicker createComponent() {
        return factory.create(GooglePlacePicker.class);
    }

    @Override
    public void loadComponent() {
        super.loadComponent();

        loadBoolean(element, "clearButtonVisible", resultComponent::setClearButtonVisible);
        loadString(element, "apiKey", resultComponent::setApiKey);

        getDataLoaderSupport().loadValueItemsQuery(resultComponent, element);
        getDataLoaderSupport().loadData(resultComponent, element);

        // These properties are loaded after the data provider is loaded,
        // because setting the data provider resets the value of the readOnly attribute to default.
        componentLoader().loadValueAndElementAttributes(resultComponent, element);
        componentLoader().loadTitle(resultComponent, element, context);
        componentLoader().loadRequired(resultComponent, element, context);
    }

    protected DataLoaderSupport getDataLoaderSupport() {
        if (dataLoaderSupport == null) {
            dataLoaderSupport = applicationContext.getBean(DataLoaderSupport.class, context);
        }
        return dataLoaderSupport;
    }
}
