package vip.xioix.aev.library;

import android.widget.EditText;

import vip.xioix.aev.library.validator.OrValidator;
import vip.xioix.aev.library.validator.Validator;

/**
 * Created by terge on 16-12-8.
 */

public class ValidatorHelper {

    private EditText mEt;
    private ValidatorHelper(EditText editText){
        mEt = editText;
    }

    public static ValidatorHelper from(EditText editText){
        return new ValidatorHelper(editText);
    }

    private Validator mValidator;
    public ValidatorHelper set(Validator validator){
        mValidator = validator;
        return this;
    }

    public ValidatorHelper or(Validator validator){
        if(mValidator == null){
            mValidator = validator;
        }else {
            mValidator = new OrValidator(mValidator,validator);
        }

        return this;
    }

    private String fixText = null;
    private boolean isNeedFix = false;
    public ValidatorHelper fix(String fix){
        isNeedFix = true;
        fixText = fix;
        return this;
    }


    public boolean check(boolean showError){
        boolean result =  mValidator.isValid(mEt);
        if(showError){
            mEt.requestFocus();
            mEt.setError(mValidator.getErrorMessage());
            if(isNeedFix)mEt.setText(fixText);
        }
        return result;
    }
}
