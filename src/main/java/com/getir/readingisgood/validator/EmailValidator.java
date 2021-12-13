package com.getir.readingisgood.validator;

import com.getir.readingisgood.utils.AppUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
public class EmailValidator implements ConstraintValidator<Email, String> {

    private static final String EMAIL_REGEX = "^[a-z0-9_-]((\\.)?[a-z0-9_-])+@(([a-z0-9_-]((\\.)?[a-z0-9_-])+\\.(aero|asia|biz|cat|com|com\\.tr|coop|info|int|jobs|mobi|museum|name|net|org|post|pro|tel|travel|edu|gov|mil|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cs|cu|cv|cx|cy|cz|dd|de|dj|dk|dm|do|dz|ec|ee|eg|eh|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|ss|st|su|sv|sx|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw))|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}:[0-9]{1,5}))$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    private static final List<String> EMAIL_CHECK_ARRAY = Arrays.asList("aaaaaaaaaa", "aaaaaaaaa", "aaaaaaaa", "aaaaaaa", "aaaaaa", "aaaaa", "aaaa", "aaa", "bbbbbbbbbb", "bbbbbbbbb", "bbbbbbbb", "bbbbbbb", "bbbbbb", "bbbbb", "bbbb", "bbb", "cccccccccc", "ccccccccc", "cccccccc", "ccccccc", "cccccc", "ccccc", "cccc", "ccc", "çççççççççç", "ççççççççç", "çççççççç", "ççççççç", "çççççç", "ççççç", "çççç", "ççç", "dddddddddd", "ddddddddd", "dddddddd", "ddddddd", "dddddd", "ddddd", "dddd", "ddd", "eeeeeeeeee", "eeeeeeeee", "eeeeeeee", "eeeeeee", "eeeeee", "eeeee", "eeee", "eee", "ffffffffff", "fffffffff", "ffffffff", "fffffff", "ffffff", "fffff", "ffff", "fff", "gggggggggg", "ggggggggg", "gggggggg", "ggggggg", "gggggg", "ggggg", "gggg", "ggg", "hhhhhhhhhh", "hhhhhhhhh", "hhhhhhhh", "hhhhhhh", "hhhhhh", "hhhhh", "hhhh", "hhh", "iiiiiiiiii", "iiiiiiiii", "iiiiiiii", "iiiiiii", "iiiiii", "iiiii", "iiii", "iii", "jjjjjjjjjj", "jjjjjjjjj", "jjjjjjjj", "jjjjjjj", "jjjjjj", "jjjjj", "jjjj", "jjj", "kkkkkkkkkk", "kkkkkkkkk", "kkkkkkkk", "kkkkkkk", "kkkkkk", "kkkkk", "kkkk", "kkk", "llllllllll", "lllllllll", "llllllll", "lllllll", "llllll", "lllll", "llll", "lll", "mmmmmmmmmm", "mmmmmmmmm", "mmmmmmmm", "mmmmmmm", "mmmmmm", "mmmmm", "mmmm", "mmm", "nnnnnnnnnn", "nnnnnnnnn", "nnnnnnnn", "nnnnnnn", "nnnnnn", "nnnnn", "nnnn", "nnn", "oooooooooo", "ooooooooo", "oooooooo", "ooooooo", "oooooo", "ooooo", "oooo", "ooo", "öööööööööö", "ööööööööö", "öööööööö", "ööööööö", "öööööö", "ööööö", "öööö", "ööö", "pppppppppp", "ppppppppp", "pppppppp", "ppppppp", "pppppp", "ppppp", "pppp", "ppp", "qqqqqqqqqq", "qqqqqqqqq", "qqqqqqqq", "qqqqqqq", "qqqqqq", "qqqqq", "qqqq", "qqq", "rrrrrrrrrr", "rrrrrrrrr", "rrrrrrrr", "rrrrrrr", "rrrrrr", "rrrrr", "rrrr", "rrr", "ssssssssss", "sssssssss", "ssssssss", "sssssss", "ssssss", "sssss", "ssss", "sss", "tttttttttt", "ttttttttt", "tttttttt", "ttttttt", "tttttt", "ttttt", "tttt", "ttt", "uuuuuuuuuu", "uuuuuuuuu", "uuuuuuuu", "uuuuuuu", "uuuuuu", "uuuuu", "uuuu", "uuu", "üüüüüüüüüü", "üüüüüüüüü", "üüüüüüüü", "üüüüüüü", "üüüüüü", "üüüüü", "üüüü", "üüü", "vvvvvvvvvv", "vvvvvvvvv", "vvvvvvvv", "vvvvvvv", "vvvvvv", "vvvvv", "vvvv", "vvv", "wwwwwwwwww", "wwwwwwwww", "wwwwwwww", "wwwwwww", "wwwwww", "wwwww", "wwww", "www", "xxxxxxxxxx", "xxxxxxxxx", "xxxxxxxx", "xxxxxxx", "xxxxxx", "xxxxx", "xxxx", "xxx", "yyyyyyyyyy", "yyyyyyyyy", "yyyyyyyy", "yyyyyyy", "yyyyyy", "yyyyy", "yyyy", "yyy", "zzzzzzzzzz", "zzzzzzzzz", "zzzzzzzz", "zzzzzzz", "zzzzzz", "zzzzz", "zzzz", "zzz", "NULL", "DUPLICATE", "0", "----------", "---------", "--------", "-------", "------", "-----", "----", "---", "--", "Dummy", "-", "Do Not Use", "To Be Remo", "Remove", "Delete", "999", "Not Provid", "none", "UNKNOWN", "1111111", "2222222", "3333333", "4444444", "5555555", "6666666", "7777777", "8888888", "9999999", "0000000", "1234567", "BOŞ", "YOK", "Belirtilmemis", "Bilinmiyor", "EMPTY", "NULL");

    @Override
    public void initialize(Email constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (AppUtil.isNullOrEmpty(value)) {
            return false;
        }

        return EMAIL_PATTERN.matcher(value).matches() && !EMAIL_CHECK_ARRAY.contains(value.split("@")[0]);
    }
}
