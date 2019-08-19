package com.trendyol.shoppingcart;

import com.openpojo.random.RandomFactory;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoMethod;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.Tester;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

/**
 * Created by Burak Köken on 16.8.2019.
 */
public abstract class BaseTest {

    @Test
    public void shouldPassGettersAndSetters() {
        Optional<Class<?>> classOptional = Optional.ofNullable(getPojoClass());
        if(classOptional.isPresent()) {
            Validator validator = ValidatorBuilder.create()
                    .with(new GetterTester())
                    .with(new SetterTester())
                    .with(new Tester() {
                        @Override
                        public void run(PojoClass pojoClass) {
                            if(pojoClass.isAbstract()) {
                                return;
                            }
                            List<PojoMethod> constructorMethodList = pojoClass.getPojoConstructors();
                            for(PojoMethod constructorMethod : constructorMethodList) {
                                Class<?>[] constructorParameterTypes = constructorMethod.getParameterTypes();
                                int constructorParameterCount = constructorParameterTypes.length;
                                Object[] constructorParameterValues = new Object[constructorParameterCount];
                                for(int index = 0; index < constructorParameterCount; index++) {
                                    constructorParameterValues[index] = RandomFactory.getRandomValue(constructorParameterTypes[index]);
                                }
                                constructorMethod.invoke(null, constructorParameterValues);
                            }
                        }
                    })
                    .build();
            validator.validate(PojoClassFactory.getPojoClass(classOptional.get()));
        }
    }

    protected abstract Class<?> getPojoClass();

}
