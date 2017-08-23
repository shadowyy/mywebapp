package codegen.javapoet.codeblock;

import com.squareup.javapoet.TypeSpec;

/**
 * @author shadowyy
 * @version 2017/8/23 15:26
 */
public class TestCodeBlock {
    public static void main(String[] args) throws Exception {

    }

    private void brewSetContentView1Method(TypeSpec.Builder builder) {
        //final CodeBlock.Builder codeBlock = CodeBlock.builder();
        //if (!StringUtils.isEmpty(mViewInjectorName)) {
        //    codeBlock.addStatement("$L.inject(this, this)", mViewInjectorName);
        //}
        //builder.addMethod(MethodSpec.methodBuilder("setContentView")
        //        .addAnnotation(Override.class)
        //        .addModifiers(Modifier.PUBLIC)
        //        .addParameter(ParameterSpec.builder(Integer.TYPE, "layoutResId")
        //                .build())
        //        .addStatement("super.setContentView(layoutResId)")
        //        .addCode(codeBlock.build())
        //        .build());
    }

}
