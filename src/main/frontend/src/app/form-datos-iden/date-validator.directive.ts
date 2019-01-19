import { Directive, forwardRef, Attribute } from '@angular/core';
import { Validator, AbstractControl, NG_VALIDATORS } from '@angular/forms';
@Directive({
    selector: '[validateBetweenRange][formControlName],[validateBetweenRange][formControl],[validateBetweenRange][ngModel]',
    providers: [
        { provide: NG_VALIDATORS, useExisting: forwardRef(() => DateValidator), multi: true }
    ]
})
export class DateValidator implements Validator {
    constructor(@Attribute('validateBetweenRange') public validateBetweenRange: string) {
    }

    validate(c: AbstractControl): {} {
        // self value (e.g. retype password)
        let v = c.value;

        // value not equal
        if (new Date(v).getTime() < new Date(1979,12,1).getTime() || new Date(v).getTime() > new Date().getTime()) return {
            validateBetweenRange: false
        }
        return null;
    }
}
